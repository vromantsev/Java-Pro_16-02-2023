package ua.hillel.bst;

import java.util.Objects;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

    private int size;
    private Node<T> root;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean insert(T element) {
        Objects.requireNonNull(element);
        // root == null
        if (root == null) {
            root = new Node<>(element);
            size++;
            return true;
        }
        // tree is not empty
        return insert(element, root);
    }

    private boolean insert(T element, Node<T> node) {
        int result = element.compareTo(node.getElement());
        if (result > 0) { // element > node.getElement()
            var right = node.getRight();
            if (right == null) { // node does not have right child
                node.setRight(new Node<>(element));
                size++;
                return true;
            } else { // node has right child
                return insert(element, node.getRight());
            }
        } else if (result < 0) { // element < node.getElement()
            var left = node.getLeft();
            if (left == null) {
                node.setLeft(new Node<>(element));
                size++;
                return true;
            } else {
                return insert(element, node.getLeft());
            }
        } else { // element = node.getElement()
            return false;
        }
    }

    @Override
    public boolean contains(T element) {
        return false;
    }

    @Override
    public T findMin() {
        return null;
    }

    @Override
    public T findMax() {
        return null;
    }

    @Override
    public int depth() {
        return 0;
    }

    @Override
    public void inOrderTraversal() {

    }
}
