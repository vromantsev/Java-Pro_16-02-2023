package ua.hillel.hw.task20;

public interface ThreadSafeList<T extends Comparable<? super T>> {

    void add(T element);

    T get(int index);

    boolean remove(T element);

}
