package org.example.examples;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

/** Четыре примера из теории; каждый использует свой каталог внутри data/examples. */
public final class TheoryExamples {
    private TheoryExamples() { }

    public static void main(String[] args) {
        try {
            Path directory = Paths.get("data", "examples");
            Files.createDirectories(directory);
            Path source = directory.resolve("readme.txt");
            Files.write(source, "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijkl\n"
                    .getBytes(StandardCharsets.US_ASCII));
            printFileInfo(source.toFile());
            printDirectory(directory.toFile());
            readParts(source);
            System.out.println("Введите не менее 12 ASCII-символов (например, Hello world!):");
            writeParts(System.in, directory);
        } catch (IOException e) {
            System.err.println("Ошибка ввода/вывода: " + e.getMessage());
        }
    }

    public static void printFileInfo(File file) {
        System.out.println("Имя: " + file.getName());
        System.out.println("Размер: " + file.length() + " байт");
        System.out.println("Последнее изменение: " + new java.util.Date(file.lastModified()));
        System.out.println("Родитель: " + file.getParent());
        System.out.println("Путь: " + file.getPath());
        System.out.println("Абсолютный путь: " + file.getAbsolutePath());
        System.out.println("Существует: " + file.exists());
        System.out.println("Чтение: " + file.canRead() + "; запись: " + file.canWrite());
        System.out.println("Каталог: " + file.isDirectory() + "; обычный файл: " + file.isFile());
        System.out.println("Абсолютный путь задан: " + file.isAbsolute());
    }

    public static void printDirectory(File directory) throws IOException {
        String[] names = directory.list();
        if (names == null) {
            throw new IOException("Не удалось прочитать каталог: " + directory);
        }
        Arrays.sort(names);
        for (String name : names) {
            File child = new File(directory, name);
            System.out.println(name + (child.isDirectory() ? " — каталог" : " — файл"));
        }
    }

    public static void readParts(Path source) throws IOException {
        try (InputStream input = new FileInputStream(source.toFile())) {
            // available() подходит здесь для маленького локального демонстрационного файла,
            // но в общем случае не определяет длину произвольного потока.
            int size = input.available();
            System.out.println("Доступно байт: " + size);
            for (int i = 0; i < size / 4; i++) {
                int value = input.read();
                if (value == -1) {
                    throw new IOException("Неожиданный конец файла");
                }
                System.out.print((char) value);
            }
            System.out.println();
            byte[] buffer = new byte[size / 8];
            readFully(input, buffer);
            System.out.println("Следующая 1/8: " + new String(buffer, StandardCharsets.US_ASCII));
            long remaining = size / 4;
            while (remaining > 0) {
                long skipped = input.skip(remaining);
                if (skipped == 0) {
                    if (input.read() == -1) {
                        throw new IOException("Неожиданный конец файла при пропуске");
                    }
                    skipped = 1;
                }
                remaining -= skipped;
            }
            readFully(input, buffer);
            System.out.println("1/8 после пропуска 1/4: "
                    + new String(buffer, StandardCharsets.US_ASCII));
            System.out.println("Осталось байт: " + input.available());
        }
    }

    public static void writeParts(InputStream input, Path directory) throws IOException {
        byte[] buffer = new byte[12];
        readFully(input, buffer);
        try (OutputStream even = new FileOutputStream(directory.resolve("1st file.txt").toFile());
             OutputStream all = new FileOutputStream(directory.resolve("2nd file.txt").toFile());
             OutputStream middle = new FileOutputStream(directory.resolve("3rd file.txt").toFile())) {
            for (int i = 0; i < buffer.length; i += 2) {
                even.write(buffer[i]);
            }
            all.write(buffer);
            middle.write(buffer, buffer.length / 4, buffer.length / 2);
        }
        System.out.println("Записаны байты на чётных позициях, все 12 байт и средние 6 байт.");
    }

    private static void readFully(InputStream input, byte[] buffer) throws IOException {
        int offset = 0;
        while (offset < buffer.length) {
            int count = input.read(buffer, offset, buffer.length - offset);
            if (count == -1) {
                throw new IOException("Недостаточно байтов для заполнения буфера");
            }
            offset += count;
        }
    }
}
