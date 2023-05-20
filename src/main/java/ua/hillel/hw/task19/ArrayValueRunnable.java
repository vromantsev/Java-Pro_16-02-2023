package ua.hillel.hw.task19;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayValueRunnable implements Runnable {

    private final float[] values;

    public ArrayValueRunnable(float[] values) {
        this.values = values;
    }

    @Override
    public void run() {
        for (int i = 0; i < values.length; i++) {
            values[i] = generateNumberByFormula();
        }
    }

    private float generateNumberByFormula() {
        return ThreadLocalRandom.current().nextFloat(this.values.length);
    }
}
