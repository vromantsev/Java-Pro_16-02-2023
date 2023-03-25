package ua.hillel.hw.task9;

import ua.hillel.hw.task9.calculator.ArrayValueCalculator;
import ua.hillel.hw.task9.calculator.Calculator;

public class CalculatorDemo {
    public static void main(String[] args) {
        String[][] values = {
                {"1","2","3","4"},
                {"1","2","3","4"},
                {"1","2","3","4"},
                {"1","2","3","4"}
        };
        Calculator calculator = new ArrayValueCalculator();
        var result = calculator.calculate(values);
        System.out.println("Sum of matrix: " + result);
    }
}
