package ua.hillel.lifo;

import java.util.EmptyStackException;
import java.util.Objects;

public class LinkedStack<T> implements Stack<T> {

    private int size;
    private Node<T> head;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(T element) {
        Objects.requireNonNull(element);
        var newNode = new Node<>(element);
        if (this.head == null) {
            this.head = newNode;
        } else {
            newNode.setNext(this.head);
            this.head = newNode;
        }
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T oldValue = this.head.getValue();
        this.head = this.head.getNext();
        this.size--;
        return oldValue;
    }
}
