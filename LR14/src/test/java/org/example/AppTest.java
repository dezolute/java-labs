package org.example;

import junit.framework.TestCase;
import org.example.examples.TheoryExamples;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppTest extends TestCase {
    private Path directory;

    @Override
    protected void setUp() throws IOException {
        Files.createDirectories(Paths.get("target"));
        directory = Files.createTempDirectory(Paths.get("target"), "lr14-test-");
    }

    @Override
    protected void tearDown() throws IOException {
        List<Path> paths;
        try (Stream<Path> stream = Files.walk(directory)) {
            paths = stream.sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        }
        for (Path path : paths) {
            Files.delete(path);
        }
    }

    public void testAllFiveTasksReadTheirFiles() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        App app = new App(directory,
                new Scanner("-9 -4 -3 0 1 2 3 4 6 12 1 2 3 4 5 6 7 8 9 10"),
                new PrintStream(bytes, true, "UTF-8"), new Random(13));
        for (int task = 1; task <= 5; task++) {
            app.runTask(task);
        }
        assertTrue(FileTasks.readText(directory.resolve("task1/voleybol.txt")).contains("волейбол"));
        assertEquals(10, FileTasks.readIntegers(directory.resolve("task2/f.txt")).size());
        assertEquals(20, FileTasks.readIntegers(directory.resolve("task3/f.txt")).size());
        assertEquals(10, FileTasks.readDistances(directory.resolve("task4/f.txt")).size());
        assertEquals(10, FileTasks.readIntegers(directory.resolve("task5/f.txt")).size());
        String output = bytes.toString("UTF-8");
        assertTrue(output.contains("Количество чисел, кратных 3: 6"));
        assertTrue(output.contains("Среднее расстояние: 5.5 км"));
        assertTrue(output.contains("Количество чисел, кратных 4: 6"));
    }

    public void testPrimeBoundariesAndIntegerLimits() {
        for (int value : new int[] {Integer.MIN_VALUE, -7, 0, 1, 4, 9, 25, 49, Integer.MAX_VALUE - 1}) {
            assertFalse("Не простое: " + value, FileTasks.isPrime(value));
        }
        for (int value : new int[] {2, 3, 5, 97, Integer.MAX_VALUE}) {
            assertTrue("Простое: " + value, FileTasks.isPrime(value));
        }
    }

    public void testPrimePartitionPreservesOrderAndDuplicates() throws IOException {
        Path source = directory.resolve("f.txt");
        Path primes = directory.resolve("g.txt");
        Path others = directory.resolve("h.txt");
        FileTasks.writeIntegers(source, Arrays.asList(-3, 0, 1, 2, 2, 3, 4, 49, 97));
        FileTasks.splitPrimes(source, primes, others);
        assertEquals(Arrays.asList(2, 2, 3, 97), FileTasks.readIntegers(primes));
        assertEquals(Arrays.asList(-3, 0, 1, 4, 49), FileTasks.readIntegers(others));
        FileTasks.writeIntegers(source, Arrays.asList(0, 1));
        FileTasks.splitPrimes(source, primes, others);
        assertTrue(FileTasks.readIntegers(primes).isEmpty());
        assertEquals(Arrays.asList(0, 1), FileTasks.readIntegers(others));
    }

    public void testMultiplesIncludeZeroAndNegativeValues() {
        assertEquals(4L, FileTasks.countMultiples(Arrays.asList(-12, -3, 0, 1, 6), 3));
        assertEquals(3L, FileTasks.countMultiples(Arrays.asList(-8, -4, 0, 3, 7), 4));
    }

    public void testDistancesAndInvalidFiles() throws IOException {
        Path file = directory.resolve("f.txt");
        FileTasks.writeText(file, "0 1,5\n3.0\n");
        assertEquals(1.5, FileTasks.average(FileTasks.readDistances(file)), 1e-12);
        assertEquals(Double.MAX_VALUE,
                FileTasks.average(Arrays.asList(Double.MAX_VALUE, Double.MAX_VALUE)), 0.0);
        for (String invalid : Arrays.asList("abc", "NaN", "Infinity", "-1")) {
            FileTasks.writeText(file, invalid);
            try {
                FileTasks.readDistances(file);
                fail("Ожидалась ошибка расстояния: " + invalid);
            } catch (IOException expected) {
                assertTrue(expected.getMessage().contains(invalid));
            }
        }
        FileTasks.writeText(file, "1 2 broken 4");
        try {
            FileTasks.readIntegers(file);
            fail("Ожидалась ошибка целого числа");
        } catch (IOException expected) {
            assertTrue(expected.getMessage().contains("broken"));
        }
        try {
            FileTasks.readIntegers(directory.resolve("missing.txt"));
            fail("Ожидалась ошибка отсутствующего файла");
        } catch (IOException expected) {
            assertNotNull(expected.getMessage());
        }
        try {
            FileTasks.average(Collections.<Double>emptyList());
            fail("Ожидалась ошибка пустого списка");
        } catch (IllegalArgumentException expected) {
            assertNotNull(expected.getMessage());
        }
    }

    public void testInputRetriesForInvalidNumbers() throws IOException {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        App app = new App(directory,
                new Scanner("bad 2147483648 0 1 2 3 4 5 6 7 8 9 NaN -1 Infinity 1,5 2 3 4 5 6 7 8 9 10"),
                new PrintStream(bytes, true, "UTF-8"), new Random(13));
        app.runTask(2);
        app.runTask(4);
        assertEquals(Integer.valueOf(0), FileTasks.readIntegers(directory.resolve("task2/f.txt")).get(0));
        assertEquals(5.55, FileTasks.average(FileTasks.readDistances(directory.resolve("task4/f.txt"))), 1e-12);
        assertTrue(bytes.toString("UTF-8").contains("Некорректное целое число"));
    }

    public void testTheoryByteOutputAndEarlyEndOfInput() throws IOException {
        TheoryExamples.writeParts(new ByteArrayInputStream("Hello world!".getBytes(StandardCharsets.US_ASCII)), directory);
        assertEquals("Hlowrd", FileTasks.readText(directory.resolve("1st file.txt")));
        assertEquals("Hello world!", FileTasks.readText(directory.resolve("2nd file.txt")));
        assertEquals("lo wor", FileTasks.readText(directory.resolve("3rd file.txt")));
        try {
            TheoryExamples.writeParts(new ByteArrayInputStream(new byte[3]), directory);
            fail("Ожидалась ошибка неполного ввода");
        } catch (IOException expected) {
            assertTrue(expected.getMessage().contains("Недостаточно"));
        }
    }
}
