package nextstep.fp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStudy {

    private static final int MIN_WORD_LENGTH = 12;
    private static final int MAX_WORDS = 100;

    public static long countWords() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        return words.stream()
                .filter(word -> word.length() > MIN_WORD_LENGTH)
                .count();
    }

    public static void printLongestWordTop100() throws IOException {
        String contents = Files.readString(Paths
                .get("src/main/resources/fp/war-and-peace.txt"));
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

        words.stream()
                .filter(word -> word.length() > MIN_WORD_LENGTH)
                .sorted(Comparator.comparingInt(String::length).reversed())
                .distinct()
                .limit(MAX_WORDS)
                .map(String::toLowerCase)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static List<Integer> doubleNumbers(List<Integer> numbers) {
        return numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
    }

    public static long sumAll(List<Integer> numbers) {
        return numbers.stream().reduce(0, (x, y) -> x + y);
    }

    public static long sumOverThreeAndDouble(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .map(number -> number * 2)
                .mapToInt(Integer::intValue)
                .sum();
    }
}