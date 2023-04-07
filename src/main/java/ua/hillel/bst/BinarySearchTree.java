package ua.hillel.bst;

public interface BinarySearchTree<T> {

    int size();

    boolean isEmpty();

    boolean insert(T element);

    boolean contains(T element);

    T findMin();

    T findMax();

    int depth();

    void inOrderTraversal();

}
