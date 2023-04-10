package ua.hillel.bst;

public class TreeDemo {
    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new RecursiveBinarySearchTree<>();
        bst.insert(50);
        bst.insert(45);
        bst.insert(41);
        bst.insert(43);
        bst.insert(46);
        bst.insert(55);
        bst.insert(56);
        bst.insert(58);
        bst.insert(60);
        bst.insert(61);

        System.out.println(bst.contains(100));
        System.out.println(bst.findMin()); // 41
        System.out.println(bst.findMax()); // 60
        System.out.println(bst.depth()); // 4
        bst.inOrderTraversal();
    }
}
