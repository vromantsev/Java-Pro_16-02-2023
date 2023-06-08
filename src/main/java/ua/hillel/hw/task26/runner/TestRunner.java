package ua.hillel.hw.task26.runner;

import ua.hillel.hw.task26.annotations.AfterSuite;
import ua.hillel.hw.task26.annotations.BeforeSuite;
import ua.hillel.hw.task26.annotations.Test;
import ua.hillel.hw.task26.utils.ReflectionUtils;

import java.util.logging.Logger;

public class TestRunner {

    private static final Logger LOGGER = Logger.getLogger(TestRunner.class.getName());

    private TestRunner() {}

    public static void start(final Class<?> testClass) {
        var testClassInstance = ReflectionUtils.instantiate(testClass);
        var beforeSuiteMethods = ReflectionUtils.findAnnotatedMethods(testClass, BeforeSuite.class);
        if (beforeSuiteMethods.size() > 1) {
            throw new IllegalArgumentException("Test class %s should have only one method annotated with @BeforeSuite!".formatted(testClass.getName()));
        }
        var afterSuiteMethods = ReflectionUtils.findAnnotatedMethods(testClass, AfterSuite.class);
        if (afterSuiteMethods.size() > 1) {
            throw new IllegalArgumentException("Test class %s should have only one method annotated with @AfterSuite!".formatted(testClass.getName()));
        }
        if (beforeSuiteMethods.isEmpty()) {
            LOGGER.info("No method annotated with @BeforeSuite are found for class %s".formatted(testClass.getName()));
        } else {
            ReflectionUtils.invokeMethods(beforeSuiteMethods, testClassInstance);
        }
        var testMethods = ReflectionUtils.findAnnotatedMethods(testClass, Test.class);
        if (!testMethods.isEmpty()) {
            var methodsOrderByPriority = ReflectionUtils.orderByPriority(testMethods);
            ReflectionUtils.invokeMethods(methodsOrderByPriority.values(), testClassInstance);
        }
        if (afterSuiteMethods.isEmpty()) {
            LOGGER.info("No methods annotated with @AfterSuite are found for class %s".formatted(testClass.getName()));
        } else {
            ReflectionUtils.invokeMethods(afterSuiteMethods, testClassInstance);
        }
    }
}
