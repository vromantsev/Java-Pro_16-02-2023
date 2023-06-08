package ua.hillel.hw.task26;

import ua.hillel.hw.task26.runner.TestRunner;
import ua.hillel.hw.task26.test.TestClass;

public class TestClassRunnerDemo {
    public static void main(String[] args) {
        TestRunner.start(TestClass.class);
    }
}
