package ua.hillel.lifo;

public interface Stack<T> {

    int size();

    boolean isEmpty();

    void push(T element);

    T pop();

}
