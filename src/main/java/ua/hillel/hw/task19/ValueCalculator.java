package ua.hillel.hw.task19;

import java.util.logging.Logger;

public class ValueCalculator {

    private static final Logger LOGGER = Logger.getLogger(ValueCalculator.class.getName());

    private static final int DEFAULT_SIZE = 1000_000;
    private static final int HALF_SIZE = DEFAULT_SIZE / 2;

    private final float[] numbers;

    public ValueCalculator() {
        this.numbers = new float[DEFAULT_SIZE];
    }

    public void run() {
        synchronized (this) {
            long startMillis = System.currentTimeMillis();
            float[] left = new float[HALF_SIZE];
            float[] right = new float[HALF_SIZE];
            System.arraycopy(this.numbers, 0, left, 0, HALF_SIZE);
            System.arraycopy(this.numbers, 0, right, 0, HALF_SIZE);
            var leftThread = new Thread(new ArrayValueRunnable(left));
            var rightThread = new Thread(new ArrayValueRunnable(right));
            try {
                leftThread.start();
                rightThread.start();
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.arraycopy(left, 0, numbers, 0, HALF_SIZE);
            System.arraycopy(right, 0, numbers, HALF_SIZE, HALF_SIZE);
            long endMillis = System.currentTimeMillis();
            LOGGER.info("Finished array processing after %d millis".formatted(endMillis - startMillis));
        }
    }

    public float[] getNumbers() {
        return this.numbers;
    }
}
