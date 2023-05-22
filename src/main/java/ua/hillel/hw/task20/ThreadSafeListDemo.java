package ua.hillel.hw.task20;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadSafeListDemo {
    public static void main(String[] args) {
        var executorService = Executors.newFixedThreadPool(10);
        ThreadSafeList<String> threadSafeList = new ThreadSafeArrayList<>(100);
        int size = 0;
        while (size < 100) {
            var result = executorService.submit(() -> threadSafeList.add(String.valueOf(ThreadLocalRandom.current().nextInt(100))));
            while (!result.isDone()) {}
            System.out.println(size + ": " + threadSafeList.get(size));
            size++;
        }


        if (!executorService.isTerminated()) {
            executorService.shutdownNow();
        }
        System.out.println(threadSafeList.remove(String.valueOf(size - 5)));
    }
}
