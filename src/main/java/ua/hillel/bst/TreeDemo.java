package ua.hillel.bst;

public class TreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(50);
        bst.insert(45);
        bst.insert(44);
        bst.insert(43);
        bst.insert(46);
        bst.insert(55);
        bst.insert(56);
        System.out.println(bst.findMin());
        System.out.println(bst.findMax());
        System.out.println(bst.depth());
        bst.inOrderTraversal();
    }
}
