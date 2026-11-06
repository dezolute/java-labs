package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Файловые операции и вычисления для варианта 13. Все текстовые файлы — UTF-8. */
public final class FileTasks {
    private FileTasks() { }

    public static void writeText(Path file, String text) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write(text);
        }
    }

    public static String readText(Path file) throws IOException {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            char[] buffer = new char[1024];
            int count;
            while ((count = reader.read(buffer)) != -1) {
                result.append(buffer, 0, count);
            }
        }
        return result.toString();
    }

    public static void writeIntegers(Path file, List<Integer> numbers) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (int value : numbers) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        }
    }

    public static List<Integer> readIntegers(Path file) throws IOException {
        List<Integer> result = new ArrayList<>();
        for (String token : readTokens(file)) {
            try {
                result.add(Integer.parseInt(token));
            } catch (NumberFormatException e) {
                throw new IOException("В файле " + file + " неверное целое число: " + token, e);
            }
        }
        return result;
    }

    public static long countMultiples(List<Integer> numbers, int divisor) {
        if (divisor == 0) {
            throw new IllegalArgumentException("Делитель не может быть нулём");
        }
        long count = 0;
        for (int value : numbers) {
            if (value % divisor == 0) {
                count++;
            }
        }
        return count;
    }

    public static boolean isPrime(int value) {
        if (value < 2) {
            return false;
        }
        if (value % 2 == 0) {
            return value == 2;
        }
        // value / divisor вместо divisor * divisor исключает переполнение int.
        for (int divisor = 3; divisor <= value / divisor; divisor += 2) {
            if (value % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public static void splitPrimes(Path source, Path primesFile, Path othersFile) throws IOException {
        List<Integer> numbers = readIntegers(source);
        List<Integer> primes = new ArrayList<>();
        List<Integer> others = new ArrayList<>();
        for (int value : numbers) {
            (isPrime(value) ? primes : others).add(value);
        }
        writeIntegers(primesFile, primes);
        writeIntegers(othersFile, others);
    }

    public static void writeDistances(Path file, List<Double> values) throws IOException {
        for (double value : values) {
            checkDistance(value);
        }
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            for (double value : values) {
                writer.write(Double.toString(value));
                writer.newLine();
            }
        }
    }

    public static List<Double> readDistances(Path file) throws IOException {
        List<Double> result = new ArrayList<>();
        for (String token : readTokens(file)) {
            try {
                double value = Double.parseDouble(token.replace(',', '.'));
                checkDistance(value);
                result.add(value);
            } catch (IllegalArgumentException e) {
                throw new IOException("В файле " + file + " неверное расстояние: " + token, e);
            }
        }
        return result;
    }

    public static double average(List<Double> values) {
        if (values.isEmpty()) {
            throw new IllegalArgumentException("Нельзя найти среднее пустого списка");
        }
        double mean = 0;
        int count = 0;
        for (double value : values) {
            checkDistance(value);
            mean += (value - mean) / ++count;
        }
        return mean;
    }

    private static void checkDistance(double value) {
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException("Расстояние должно быть конечным и неотрицательным");
        }
    }

    private static List<String> readTokens(Path file) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (!trimmed.isEmpty()) {
                    for (String token : trimmed.split("\\s+")) {
                        result.add(token);
                    }
                }
            }
        }
        return result;
    }
}
