package ua.hillel.hw.task20;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class PetrolStation {

    private static final Logger LOGGER = Logger.getLogger(PetrolStation.class.getName());

    private static final int ALLOWED_THREADS_NUMBER = 3;

    private final Semaphore semaphore = new Semaphore(ALLOWED_THREADS_NUMBER, true);
    private double amount;

    public PetrolStation(double amount) {
        this.amount = amount;
    }

    public void doRefuel() {
        try {
            semaphore.acquire();
            synchronized (this) {
                var currentAmount = this.amount;
                LOGGER.info("'%s' is refueling...".formatted(Thread.currentThread().getName()));
                var randomSeconds = ThreadLocalRandom.current().nextInt(3, 10);
                TimeUnit.SECONDS.sleep(randomSeconds);
                amount = amount - randomSeconds;
                LOGGER.info(
                        "'%s' is refueled. Station fuel level before: %f, after: '%f'"
                                .formatted(Thread.currentThread().getName(), currentAmount, amount)
                );
            }
            semaphore.release();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
