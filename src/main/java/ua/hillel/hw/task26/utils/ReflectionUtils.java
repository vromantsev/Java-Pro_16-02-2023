package ua.hillel.hw.task26.utils;

import ua.hillel.hw.task26.annotations.Order;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public final class ReflectionUtils {

    private static final int DEFAULT_PRIORITY = 0;

    private ReflectionUtils() {}

    public static void invokeMethods(final Collection<Method> methods, final Object instance) {
        Objects.requireNonNull(methods);
        Objects.requireNonNull(instance);
        methods.forEach(method -> {
            method.setAccessible(true);
            try {
                method.invoke(instance);
            } catch (IllegalAccessException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static Object instantiate(final Class<?> testClass) {
        Objects.requireNonNull(testClass);
        try {
            Constructor<?> constructor = testClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException |
                 InstantiationException e) {
            throw new RuntimeException("Cannot instantiate class %s".formatted(testClass.getName()), e);
        }
    }

    public static List<Method> findAnnotatedMethods(final Class<?> testClass, final Class<? extends Annotation> annotation) {
        Objects.requireNonNull(testClass);
        Objects.requireNonNull(annotation);
        return Arrays.stream(testClass.getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(annotation))
                .collect(Collectors.toList());
    }

    public static Map<CompositeKey, Method> orderByPriority(final List<Method> testMethods) {
        Objects.requireNonNull(testMethods);
        Map<CompositeKey, Method> priorityToMethodMap = new TreeMap<>();
        for (Method testMethod : testMethods) {
            CompositeKey key;
            if (testMethod.isAnnotationPresent(Order.class)) {
                Order order = testMethod.getDeclaredAnnotation(Order.class);
                key = new CompositeKey(order.priority(), testMethod.getName());
            } else {
                key = new CompositeKey(DEFAULT_PRIORITY, testMethod.getName());
            }
            priorityToMethodMap.put(key, testMethod);
        }
        return priorityToMethodMap;
    }

    private static final class CompositeKey implements Comparable<CompositeKey> {

        private final Integer priority;
        private final String methodName;

        private CompositeKey(Integer priority, String methodName) {
            this.priority = priority;
            this.methodName = methodName;
        }

        public Integer priority() {
            return priority;
        }

        public String methodName() {
            return methodName;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            var that = (CompositeKey) obj;
            return Objects.equals(this.priority, that.priority);
        }

        @Override
        public int hashCode() {
            return Objects.hash(priority, methodName);
        }

        @Override
        public String toString() {
            return "CompositeKey[" +
                    "priority=" + priority + ", " +
                    "methodName=" + methodName + ']';
        }

        @Override
        public int compareTo(CompositeKey key) {
            return Comparator.comparing(CompositeKey::priority)
                    .thenComparing(CompositeKey::methodName)
                    .compare(this, key);
        }
    }
}
