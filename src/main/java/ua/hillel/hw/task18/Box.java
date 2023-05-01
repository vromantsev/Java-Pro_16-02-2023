package ua.hillel.hw.task18;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

public class Box<T extends Fruit> {

    private final Class<T> fruitType;
    private final List<T> fruits = new ArrayList<>();

    public Box(Class<T> fruitType) {
        this.fruitType = fruitType;
    }

    public List<T> getFruits() {
        return fruits;
    }

    public Class<T> getFruitType() {
        return fruitType;
    }

    public void add(final T fruit) {
        BiPredicate<T, Class<T>> fruitPredicate = Fruit::isSameFruitType;
        if (fruitPredicate.test(fruit, this.fruitType)) {
            fruits.add(fruit);
            return;
        }
        throw new IllegalArgumentException("Cannot put different fruits into the same box! This box is for %s, trying to put %s".formatted(this.fruitType, fruit));
    }

    public void addAll(final List<T> fruits) {
        for (T fruit : fruits) {
            this.add(fruit);
        }
    }

    public <F extends Fruit> float getWeight() {
        float weight = 0.0f;
        for (T fruit : fruits) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public <F extends Fruit> float compareTo(Box<F> o) {
        if (this.fruits.isEmpty() || o.getFruits().isEmpty()) {
            throw new IllegalArgumentException("Box is empty.");
        }
        return Float.compare(this.getWeight(), o.getWeight());
    }

    public MergeBoxResult merge(final Box<? extends T> box) {
        var oldSize = this.fruits.size();
        for (T fruit : box.getFruits()) {
            this.add(fruit);
        }
        var successes = fruits.size() - oldSize;
        var failures = box.getFruits().size() - successes;
        return new MergeBoxResult(successes, failures);
    }

    public static class MergeBoxResult {

        private final int successes;
        private final int failures;

        private MergeBoxResult(int successes, int failures) {
            this.successes = successes;
            this.failures = failures;
        }

        public int getSuccesses() {
            return successes;
        }

        public int getFailures() {
            return failures;
        }

        public static MergeBoxResult of(int successes, int failures) {
            return new MergeBoxResult(successes, failures);
        }

        @Override
        public String toString() {
            return "MergeBoxResult{" +
                    "successes=" + successes +
                    ", failures=" + failures +
                    '}';
        }
    }
}
