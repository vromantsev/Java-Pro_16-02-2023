package ua.hillel.bst;

import java.util.Objects;
import java.util.function.Consumer;

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
        Objects.requireNonNull(element);
        return contains(root, element);
    }

    private boolean contains(Node<T> node, T element) {
        if (node == null) {
            return false;
        }
        var result = node.getElement().compareTo(element);
        if (result > 0) {
            return contains(node.getLeft(), element);
        } else if (result < 0) {
            return contains(node.getRight(), element);
        } else {
            return true;
        }
    }

    @Override
    public T findMin() {
        T min = null;
        if (this.root != null) {
            var rootCopy = this.root;
            while (rootCopy.getLeft() != null) {
                var left = rootCopy.getLeft();
                min = left.getElement();
                rootCopy = rootCopy.getLeft();
            }
        }
        return min;
    }

    @Override
    public T findMax() {
        T max = null;
        if (this.root != null) {
            var rootCopy = this.root;
            while (rootCopy.getRight() != null) {
                var right = rootCopy.getRight();
                max = right.getElement();
                rootCopy = rootCopy.getRight();
            }
        }
        return max;
    }

    @Override
    public int depth() {
        if (this.root == null) {
            return 0;
        }
        return Math.max(depth(root.getLeft()), depth(root.getRight()));
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        return Math.max(1 + depth(node.getLeft()), 1 + depth(node.getRight()));
    }

    @Override
    public void inOrderTraversal() {
        inOrderTraversal(root, value -> System.out.printf("%d ", value));
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> action) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), action);
            action.accept(node.getElement());
            inOrderTraversal(node.getRight(), action);
        }
    }
}
