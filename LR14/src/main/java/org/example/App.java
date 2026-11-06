package org.example;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/** Лабораторная работа 14, вариант 13. */
public final class App {
    private final Path directory;
    private final Scanner input;
    private final PrintStream output;
    private final Random random;

    App(Path directory, Scanner input, PrintStream output, Random random) {
        this.directory = directory;
        this.input = input;
        this.output = output;
        this.random = random;
    }

    public static void main(String[] args) {
        boolean demo = args.length == 1 && "--demo".equals(args[0]);
        if (args.length > 0 && !demo) {
            System.err.println("Запуск: org.example.App [--demo]");
            return;
        }
        Scanner scanner = demo
                ? new Scanner(new StringReader("-9 -4 -3 0 1 2 3 4 6 12 1 2 3 4 5 6 7 8 9 10"))
                : new Scanner(System.in, StandardCharsets.UTF_8.name());
        App app = new App(Paths.get("data"), scanner, System.out,
                demo ? new Random(13) : new Random());
        try {
            if (demo) {
                for (int task = 1; task <= 5; task++) {
                    app.runTask(task);
                }
            } else {
                app.runMenu();
            }
        } catch (EndOfInputException e) {
            System.err.println("Ввод завершён. " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Ошибка работы с файлом: " + e.getMessage());
        }
    }

    private void runMenu() throws IOException {
        while (true) {
            output.println("\nЛР14, вариант 13. Выберите задание:");
            output.println("1 — Любимый вид спорта: создание, свойства и чтение файла");
            output.println("2 — Десять целых чисел: количество кратных 3");
            output.println("3 — Случайные числа: простые в g, остальные в h");
            output.println("4 — Десять расстояний: среднее значение");
            output.println("5 — Десять целых чисел: количество кратных 4");
            output.println("6 — Выполнить все задания; 0 — Выход");
            int choice = readInteger();
            if (choice == 0) {
                return;
            }
            if (choice < 1 || choice > 6) {
                output.println("Введите номер от 0 до 6.");
                continue;
            }
            try {
                if (choice == 6) {
                    for (int task = 1; task <= 5; task++) {
                        runTask(task);
                    }
                } else {
                    runTask(choice);
                }
            } catch (IOException e) {
                output.println("Ошибка работы с файлом: " + e.getMessage());
            }
        }
    }

    void runTask(int task) throws IOException {
        Path folder = directory.resolve("task" + task);
        Files.createDirectories(folder);
        output.println("\nЗадание " + task);
        switch (task) {
            case 1:
                Path sport = folder.resolve("voleybol.txt");
                FileTasks.writeText(sport, "Мой любимый вид спорта — волейбол.\n"
                        + "В каждой команде на площадке играют шесть человек.\n"
                        + "Цель игры — направить мяч на площадку соперника через сетку.\n"
                        + "Волейбол развивает реакцию, координацию и умение работать в команде.\n");
                output.println("Имя: " + sport.getFileName());
                output.println("Абсолютный путь: " + sport.toAbsolutePath());
                output.println("Размер: " + Files.size(sport) + " байт");
                output.println("Последнее изменение: " + Files.getLastModifiedTime(sport));
                output.println("Можно читать: " + Files.isReadable(sport));
                output.println("Можно записывать: " + Files.isWritable(sport));
                output.println("Содержимое файла:\n" + FileTasks.readText(sport));
                break;
            case 2:
                output.println("Введите 10 целых чисел через пробел или с новой строки:");
                List<Integer> numbers = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    numbers.add(readInteger());
                }
                Path integers = folder.resolve("f.txt");
                FileTasks.writeIntegers(integers, numbers);
                List<Integer> loaded = FileTasks.readIntegers(integers);
                output.println("Числа из файла: " + loaded);
                output.println("Количество чисел, кратных 3: " + FileTasks.countMultiples(loaded, 3));
                break;
            case 3:
                List<Integer> generated = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    generated.add(random.nextInt(201) - 100);
                }
                Path source = folder.resolve("f.txt");
                Path primes = folder.resolve("g.txt");
                Path others = folder.resolve("h.txt");
                FileTasks.writeIntegers(source, generated);
                FileTasks.splitPrimes(source, primes, others);
                output.println("Исходный файл f: " + FileTasks.readIntegers(source));
                output.println("Простые числа (g): " + FileTasks.readIntegers(primes));
                output.println("Остальные числа (h): " + FileTasks.readIntegers(others));
                break;
            case 4:
                output.println("Введите 10 расстояний в километрах (точка или запятая):");
                List<Double> distances = new ArrayList<>();
                for (int i = 0; i < 10; i++) {
                    distances.add(readDistance());
                }
                Path distanceFile = folder.resolve("f.txt");
                FileTasks.writeDistances(distanceFile, distances);
                List<Double> readDistances = FileTasks.readDistances(distanceFile);
                output.println("Расстояния из файла: " + readDistances);
                output.println("Среднее расстояние: " + FileTasks.average(readDistances) + " км");
                break;
            case 5:
                Path data = folder.resolve("f.txt");
                FileTasks.writeIntegers(data, Arrays.asList(-16, -9, -4, 0, 1, 4, 7, 8, 12, 15));
                List<Integer> values = FileTasks.readIntegers(data);
                output.println("Числа из файла: " + values);
                output.println("Количество чисел, кратных 4: " + FileTasks.countMultiples(values, 4));
                break;
            default:
                throw new IllegalArgumentException("Нет такого задания: " + task);
        }
        output.println("Файлы задания: " + folder.toAbsolutePath());
    }

    private String nextToken() {
        if (!input.hasNext()) {
            throw new EndOfInputException("Недостаточно данных для завершения задания.");
        }
        return input.next();
    }

    private int readInteger() {
        while (true) {
            String token = nextToken();
            try {
                return Integer.parseInt(token);
            } catch (NumberFormatException e) {
                output.println("Некорректное целое число: " + token + ". Повторите ввод:");
            }
        }
    }

    private double readDistance() {
        while (true) {
            String token = nextToken();
            try {
                double value = Double.parseDouble(token.replace(',', '.'));
                if (Double.isFinite(value) && value >= 0) {
                    return value;
                }
            } catch (NumberFormatException e) {
                // Об ошибках формата и значения сообщаем одинаково.
            }
            output.println("Введите конечное неотрицательное расстояние вместо: " + token);
        }
    }

    private static final class EndOfInputException extends RuntimeException {
        EndOfInputException(String message) {
            super(message);
        }
    }
}
