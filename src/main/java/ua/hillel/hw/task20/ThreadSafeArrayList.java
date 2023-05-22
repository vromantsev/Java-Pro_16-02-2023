package ua.hillel.hw.task20;

import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeArrayList<T extends Comparable<? super T>> implements ThreadSafeList<T> {

    private final Lock lock = new ReentrantLock(true);
    private int size;
    private Object[] elements;

    public ThreadSafeArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be < 0!");
        }
        this.elements = new Object[size];
    }

    @Override
    public void add(T element) {
        Objects.requireNonNull(element);
        lock.lock();
        try {
            elements[size++] = element;
        } finally {
            lock.unlock();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public T get(int index) {
        Objects.checkIndex(index, this.size);
        lock.lock();
        try {
            return (T) elements[index];
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean remove(T element) {
        Objects.requireNonNull(element);
        lock.lock();
        try {
            for (int i = 0; i < elements.length; i++) {
                if (elements[i].equals(element)) {
                    System.arraycopy(elements, i + 1, elements, i, elements.length - i - 1);
                    return true;
                }
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
