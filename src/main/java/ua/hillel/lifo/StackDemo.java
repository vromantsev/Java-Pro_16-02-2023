package ua.hillel.lifo;

public class StackDemo {
    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedStack<>();

        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }

        System.out.println("Is stack empty: " + stack.isEmpty());
        System.out.println("Stack size after adding elements: " + stack.size()); // 10

        var size = stack.size();
        for (int i = 0; i < size; i++) {
            System.out.println(stack.pop());
        }
        System.out.println("Stack size after removal: " + stack.size()); // 0
    }
}
