package ua.hillel.concurrency.lesson1;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        Store store = new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);

        var t1 = new Thread(producer, "producer thread");
        var t2 = new Thread(consumer, "consumer thread");

        t1.start();
        t2.start();
    }
}
