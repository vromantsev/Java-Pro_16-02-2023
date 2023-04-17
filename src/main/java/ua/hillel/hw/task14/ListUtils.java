package ua.hillel.hw.task14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ListUtils {

    private static final String SAMPLE_TEXT_PATH = "data/text.txt";

    private ListUtils() {
    }

    public static <K, V> List<Pair<K, V>> findOccurances(final List<V> values) {
        return calcOccurance(values).entrySet().stream()
                .map(e -> (Pair<K, V>) Pair.of(e.getKey(), e.getValue()))
                .toList();
    }

    public static <T> Map<T, Long> calcOccurance(final List<T> elements) {
        Objects.requireNonNull(elements);
        return elements.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static <T> Set<T> findUnique(final List<? extends T> elements) {
        Objects.requireNonNull(elements);
        return new HashSet<>(elements);
    }

    public static <T> List<T> toList(final T[] array) {
        Objects.requireNonNull(array);
        return new ArrayList<>(Arrays.asList(array));
    }

    public static int countOccurances(final List<String> elements, final String target) {
        Objects.requireNonNull(elements);
        Objects.requireNonNull(target);
        return (int) elements.stream()
                .filter(word -> word.equals(target))
                .count();
    }

    public static List<String> readFile(final String path) {
        try (final InputStream inputStream = ListUtils.class.getClassLoader().getResourceAsStream(Objects.requireNonNull(path));
             final BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(inputStream)))) {
            String line;
            List<String> words = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                words.addAll(Arrays.asList(line.split(" ")));
            }
            return words;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Pair<K, V> {

        private final K key;
        private final V value;

        private Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public static <K, V> Pair<K, V> of(final K key, final V value) {
            return new Pair<>(key, value);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) {
        // 1.
        var words = readFile(SAMPLE_TEXT_PATH);
        var targetWord = "she";
        var occurances = countOccurances(words, targetWord);
        System.out.printf("Found %d occurances of the word %s\n", occurances, targetWord);

        // 2.
        String[] values = {"123", "good game", "nice one!", "123"};
        System.out.println("Array: " + Arrays.toString(values));
        var strings = toList(values);
        System.out.println("List: " + strings);

        // 3.
        var uniques = findUnique(Arrays.asList(values));
        System.out.println("Found unique words: " + uniques);

        // 4.
        var wordCountMap = calcOccurance(words);
        System.out.println(wordCountMap);

        // 4*.
        var result = findOccurances(words);
        System.out.println(result);
    }
}
