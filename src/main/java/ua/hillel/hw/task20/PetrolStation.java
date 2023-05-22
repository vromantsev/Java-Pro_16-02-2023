package ua.hillel.hw.task20;

import java.util.concurrent.Semaphore;

public class PetrolStation {

    private static final int ALLOWED_THREADS_NUMBER = 3;

    private final Semaphore semaphore = new Semaphore(ALLOWED_THREADS_NUMBER, true);
    private double amount;

    public void doRefuel() {
        try {
            semaphore.acquire();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            semaphore.release();
        }
    }
}
