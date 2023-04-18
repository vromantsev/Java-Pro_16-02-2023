package ua.hillel.concurrency;

public class Store {

    private int products;

    public synchronized void supply() {
        while (products >= 3) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        products++;
        System.out.println("[" + Thread.currentThread().getName() + "] Supplier added 1 product");
        System.out.println("[" + Thread.currentThread().getName() + "] Products left: " + products);
        notify();
    }

    public synchronized void get() {
        while (products < 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        products--;
        System.out.println("[" + Thread.currentThread().getName() + "] Client bought 1 product");
        System.out.println("[" + Thread.currentThread().getName() + "] Products left: " + products);
        notify();
    }
}
