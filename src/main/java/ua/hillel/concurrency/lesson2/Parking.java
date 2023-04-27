package ua.hillel.concurrency.lesson2;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Parking {

    // semaphore, count down latch, cyclic barrier, phaser, exchanger
    // wait, notify, synchronized

    private static final boolean[] PARKING_PLACES = new boolean[5];

    public static final int PERMITTED_THREADS = 5;
    private static final Semaphore SEMAPHORE = new Semaphore(PERMITTED_THREADS, true); // 5 -> 5 threads can access the resource (parking place) at the same time.

    public static void main(String[] args) throws InterruptedException {
        int threads = 10;
        for (int i = 1; i <= threads; i++) {
            new Thread(new Car(i)).start();
            TimeUnit.MILLISECONDS.sleep(500);
        }
    }

    public static class Car implements Runnable {

        private int carNumber;

        public Car(int carNumber) {
            this.carNumber = carNumber;
        }

        @Override
        public void run() {
            try {
                SEMAPHORE.acquire(); // PERMITTED_THREADS - 1
                // search a parking place
                int parkingPlaceNumber = -1;
                synchronized (PARKING_PLACES) {
                    for (int i = 0; i < PERMITTED_THREADS; i++) {
                        if (!PARKING_PLACES[i]) {  // check if parking place is free
                            PARKING_PLACES[i] = true; // car found a parking place and acquired it
                            parkingPlaceNumber = i;
                            System.out.printf("Car %d found a parking place %d\n", carNumber, i);
                            break;
                        }
                    }
                }

                TimeUnit.SECONDS.sleep(5); // shopping

                // leave the parking place
                synchronized (PARKING_PLACES) {
                    PARKING_PLACES[parkingPlaceNumber] = false; // leave a parking place
                }
                SEMAPHORE.release(); // PERMITTED_THREADS + 1
                System.out.printf("Car %d left the parking.\n", carNumber);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
