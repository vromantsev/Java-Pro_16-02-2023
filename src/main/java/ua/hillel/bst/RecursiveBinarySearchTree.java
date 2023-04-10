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
        if (node == null) { // recursion exit condition
            return false;
        }
        int result = node.getElement().compareTo(element);// compare node.getElement() vs element
        if (result > 0) { // node.getElement() > element
            return contains(node.getLeft(), element);
        } else if (result < 0) { // node.getElement() < element
            return contains(node.getRight(), element);
        } else { // node.getElement() = element
            return true;
        }
    }

    @Override
    public T findMin() {
        T min = null;
        if (this.root != null) {
            var rootCopy = this.root;
            while (rootCopy.getLeft() != null) {
                rootCopy = rootCopy.getLeft();
            }
            min = rootCopy.getElement();
        }
        return min;
    }

    @Override
    public T findMax() {
        T max = null;
        if (this.root != null) {
            var rootCopy = this.root;
            while (rootCopy.getRight() != null) {
                rootCopy = rootCopy.getRight();
            }
            max = rootCopy.getElement();
        }
        return max;
    }

    @Override
    public int depth() {
        // root == null
        if (root == null) {
            return 0;
        }
        // root != null -> iterate over left and right subtree -> left compare to right -> max depth
        return Math.max(depth(root.getLeft()), depth(root.getRight())); // left=3 vs right=4
    }

    private int depth(Node<T> node) {
        if (node == null) {
            return 0;
        }
        int leftDepth = 1 + depth(node.getLeft());
        int rightDepth = 1 + depth(node.getRight());
        return Math.max(leftDepth, rightDepth);
    }

    @Override
    public void inOrderTraversal() {
        inOrderTraversal(root, arg -> System.out.printf("%d ", arg));
    }

    private void inOrderTraversal(Node<T> node, Consumer<T> action) {
        if (node != null) {
            inOrderTraversal(node.getLeft(), action);
            action.accept(node.getElement());
            inOrderTraversal(node.getRight(), action);
        }
    }
}
