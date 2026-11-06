package org.example.task2;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class App {
    public static void main(String[] args) {
        List<Cafe> cafes = new ArrayList<>(Arrays.asList(
                new Cafe(1, "Зёрна", "Минск, ул. Ленина, 1", 3, Arrays.asList(5, 4, 5)),
                new Cafe(2, "Аромат", "Минск, ул. Кирова, 2", 2, Arrays.asList(4, 4, 4)),
                new Cafe(3, "Бисквит", "Минск, ул. Садовая, 3", 3, Arrays.asList(5, 3, 4)),
                new Cafe(4, "Уют", "Минск, ул. Лесная, 4", 2, Arrays.asList(3, 3, 3)),
                new Cafe(5, "Пауза", "Минск, ул. Полевая, 5", 1, Arrays.asList(2, 3, 2)),
                new Cafe(6, "Встреча", "Минск, ул. Мира, 6", 2, Arrays.asList(3, 4, 3))
        ));

        System.out.println("Исходный список кафе:");
        cafes.forEach(System.out::println);

        removeLowRatedCafes(cafes);
        System.out.println("\nПосле удаления кафе со средней оценкой ниже 3:");
        cafes.forEach(System.out::println);

        promoteCafes(cafes);
        System.out.println("\nПосле повышения рейтинга кафе со средней оценкой от 4:");
        cafes.forEach(System.out::println);

        int rating = 3;
        System.out.println("\nКафе с рейтингом " + rating + ":");
        printCafes(cafes, rating);

        sortCafes(cafes);
        System.out.println("\nКафе по убыванию средней оценки, при совпадении — по названию:");
        for (Cafe cafe : cafes) {
            System.out.printf("ID: %d, название: %s, средняя оценка: %.2f%n",
                    cafe.getId(), cafe.getName(), cafe.getAverageReview());
        }
    }

    public static void removeLowRatedCafes(List<Cafe> cafes) {
        Iterator<Cafe> iterator = cafes.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getAverageReview() < 3) {
                iterator.remove();
            }
        }
    }

    public static void promoteCafes(List<Cafe> cafes) {
        for (Cafe cafe : cafes) {
            if (cafe.getAverageReview() >= 4) {
                cafe.setRating(cafe.getRating() + 1);
            }
        }
    }

    public static void printCafes(List<Cafe> cafes, int rating) {
        for (Cafe cafe : cafes) {
            if (cafe.getRating() == rating) {
                System.out.println(cafe);
            }
        }
    }

    public static void sortCafes(List<Cafe> cafes) {
        Collator collator = Collator.getInstance(new Locale("ru", "RU"));
        cafes.sort(Comparator.comparingDouble(Cafe::getAverageReview)
                .thenComparing(Cafe::getName, collator).reversed());
    }
}
