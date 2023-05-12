package ua.hillel.reflection;

import ua.hillel.reflection.annotations.Inject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Objects;

public class CustomDependencyInjector<T> implements DependencyInjector<T> {

    public CustomDependencyInjector(List<T> classes) {
        Objects.requireNonNull(classes);
        // 1. analyze input classes and find those where field is annotated with @Inject annotation
        // 2. take those classes and create a new instances of them
        classes.stream()
                .filter(obj -> {
                    var clazz = obj.getClass();
                    boolean isInjectable = false;
                    for (Field f : clazz.getDeclaredFields()) {
                        if (f.isAnnotationPresent(Inject.class)) {
                            isInjectable = true;
                            break;
                        }
                    }
                    return isInjectable;
                })
                .forEach(obj -> {
                    var clazz = obj.getClass();
                    for (Field f : clazz.getDeclaredFields()) {
                        if (f.isAnnotationPresent(Inject.class)) {
                            try {
                                Object result = f.getType().getConstructor().newInstance();
                                f.setAccessible(true);
                                f.set(obj, result);
                            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException |
                                     IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                });
    }
}
