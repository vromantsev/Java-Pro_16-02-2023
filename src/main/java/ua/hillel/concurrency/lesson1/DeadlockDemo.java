package ua.hillel.concurrency.lesson1;

import java.util.concurrent.TimeUnit;

public class DeadlockDemo implements Runnable {

    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private void processFirst() {
        synchronized (monitor1) {
            System.out.println("FIRST: Obtained monitor1");
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (monitor2) {
                System.out.println("FIRST: Obtained monitor2");
            }
            System.out.println("FIRST: released monitor2");
        }
        System.out.println("FIRST: released monitor1");
    }

    private void processSecond() {
        synchronized (monitor2) {
            System.out.println("SECOND: Obtained monitor2");
            synchronized (monitor1) {
                System.out.println("SECOND: Obtained monitor1");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("SECOND: released monitor1");
        }
        System.out.println("SECOND: released monitor2");
    }

    @Override
    public void run() {
        processFirst();
        processSecond();
    }

    public static void main(String[] args) {
        DeadlockDemo deadlockDemo = new DeadlockDemo();

        var t1 = new Thread(deadlockDemo);
        var t2 = new Thread(deadlockDemo);

        t1.start();
        t2.start();
    }
}
