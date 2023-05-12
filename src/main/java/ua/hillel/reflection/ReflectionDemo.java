package ua.hillel.reflection;


import java.util.Arrays;

public class ReflectionDemo {

    // Reflection - API  (application programming interface) -> metaprogramming
    // Class -> fields, constructors, methods, constants -> get fields, methods, constructors, check modifiers
    // metadata - foundation for metaprogramming
    // Annotations

    // 1. Class<?> boxClass = Class.forName("ua.hillel.reflection.Box");
    // 2. Class<? extends Box> aBoxClass = box.getClass();
    // 3. Class<Box> aBoxClazz = Box.class;
    public static void main(String[] args) {
        Box box = new Box();
        //box.open(); // 1.

        Class<Box> boxClass = Box.class;

        // annotation

        DependencyInjector<Box> injector = new CustomDependencyInjector<>(Arrays.asList(box));

        Box innerBox = box.getBox();
        System.out.println(innerBox);

    }
}
