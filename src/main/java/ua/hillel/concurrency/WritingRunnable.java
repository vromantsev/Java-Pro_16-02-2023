package ua.hillel.concurrency;

public class WritingRunnable implements Runnable {

    private IncrementCounter counter;

    public WritingRunnable(IncrementCounter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50000; i++) {
            counter.increment();
        }
        System.out.println(Thread.currentThread().getName() + " " + Thread.activeCount());
    }
}
