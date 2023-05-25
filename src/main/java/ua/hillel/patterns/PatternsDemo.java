package ua.hillel.patterns;

// design pattern - object's description, interactions between these objects
// to solve well-known problem (in the project scope/context)

// pros
// 1. code is reusable
// 2. more effective comparing to the "homemade" solution -> less time consuming, reliable solution
// 3. No need to refactor the codebase if the pattern was used
// -> because usage of the design pattern is an optimal solution.
// 4. less code comparing to the case when no patterns were used
// 5. Higher level of qualification

// cons
// 1. overuse of design patterns
// 2. many design patterns - it is very hard to memorize all patterns
// 3. ineffective usage of desing pattern

import ua.hillel.patterns.singleton.ProductProvider;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

// 3 types:
// Creational - the type of DP which is responsible for objects creation
// Structural - allows to build a very flexible and useful objects hierarchy
// Behavioral - describes a behavior between objects, and provides effective and safe way of interaction
// between the classes
public class PatternsDemo {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ProductProvider productProvider = ProductProvider.INSTANCE;

        // serialization - transforms Java object to byte[]
        // deserialization - transforms byte[] to Java object

        try (var out = new FileOutputStream("out.ser");
             var objectOutputStream = new ObjectOutputStream(out)) {
            objectOutputStream.writeObject(productProvider);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ProductProvider anotherProductProvider = null;
        try (var in = new FileInputStream("out.ser");
             var objectInputStream = new ObjectInputStream(in)) {
            anotherProductProvider = (ProductProvider) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        System.out.println(productProvider.hashCode());
        System.out.println(anotherProductProvider.hashCode());

        if (productProvider == anotherProductProvider) {
            System.out.println("The same");
        } else {
            System.out.println("Different");
        }

        productProvider.test();
    }
}
