package ua.hillel.concurrency.lesson1;

import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private Store store;

    public Consumer(Store store) {
        this.store = store;
    }

    @Override
    public void run() {
        while (Thread.currentThread().isAlive()) {
            store.get();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
