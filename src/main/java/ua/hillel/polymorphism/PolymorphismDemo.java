package ua.hillel.polymorphism;

import java.util.Arrays;

public class PolymorphismDemo {
    public static void main(String[] args) {
        Dancer dancer = new Dancer("Bobby", 23);
        Dancer breakDanceDancer = new BreakDanceDancer("Sam", 21); // upcasting
        Dancer freeStyleDancer = new FreeStyleDancer("Joe", 22); // upcasting

        // interface
        var dancers = Arrays.asList(dancer, breakDanceDancer, freeStyleDancer);
        for (Dancer d : dancers) {
            d.dance(); // polymorphic method call
        }
    }


}
