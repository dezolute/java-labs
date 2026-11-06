package org.example.task2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;

public class CafeTest extends TestCase {
    public void testAverageReviewIncludesFractionalPartAndHandlesEmptyList() {
        assertEquals(3.5, cafe(1, "Кафе", 2, 3, 4).getAverageReview(), 0.000001);
        assertEquals(0.0, cafe(2, "Без отзывов", 1).getAverageReview(), 0.000001);
    }

    public void testRemovalKeepsExactlyThreeAndRemovesConsecutiveLowScores() {
        Cafe boundary = cafe(3, "Ровно три", 2, 3, 3);
        List<Cafe> cafes = new ArrayList<>(Arrays.asList(
                cafe(1, "Первое", 2, 2, 3), cafe(2, "Второе", 2, 1, 2), boundary));

        App.removeLowRatedCafes(cafes);

        assertEquals(Collections.singletonList(boundary), cafes);
    }

    public void testPromotionStartsAtFourAndDoesNotChangeReviews() {
        Cafe below = cafe(1, "Ниже четырёх", 2, 3, 4);
        Cafe boundary = cafe(2, "Ровно четыре", 2, 3, 5);
        Cafe above = cafe(3, "Выше четырёх", 3, 4, 5);

        App.promoteCafes(Arrays.asList(below, boundary, above));

        assertEquals(2, below.getRating());
        assertEquals(3, boundary.getRating());
        assertEquals(4, above.getRating());
        assertEquals(Arrays.asList(3, 5), boundary.getVisitorReviews());
    }

    public void testSortUsesDescendingAverageAndRussianAlphabetForTies() {
        Cafe first = cafe(1, "Якорь", 1, 5);
        Cafe second = cafe(2, "Еда", 1, 4);
        Cafe third = cafe(3, "Ёлка", 1, 4);
        Cafe fourth = cafe(4, "Жасмин", 1, 4);
        List<Cafe> cafes = new ArrayList<>(Arrays.asList(fourth, third, first, second));

        App.sortCafes(cafes);

        assertEquals(Arrays.asList(first, second, third, fourth), cafes);
    }

    public void testPrintFiltersByRatingRatherThanAverage() throws Exception {
        PrintStream original = System.out;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream capture = new PrintStream(output, true, "UTF-8");
        try {
            System.setOut(capture);
            App.printCafes(Arrays.asList(
                    cafe(1, "Выбранное", 3, 5), cafe(2, "Другое", 4, 3)), 3);
        } finally {
            System.setOut(original);
            capture.close();
        }

        String printed = output.toString("UTF-8");
        assertTrue(printed.contains("Выбранное"));
        assertFalse(printed.contains("Другое"));
    }

    public void testReviewsAreProtectedFromExternalChanges() {
        List<Integer> reviews = new ArrayList<>(Arrays.asList(4, 5));
        Cafe cafe = new Cafe(1, "Кафе", "Минск", 3, reviews);
        reviews.clear();
        assertEquals(4.5, cafe.getAverageReview(), 0.000001);
        try {
            cafe.getVisitorReviews().clear();
            fail("Список отзывов должен быть доступен только для чтения");
        } catch (UnsupportedOperationException expected) {
            assertEquals(2, cafe.getVisitorReviews().size());
        }
    }

    private Cafe cafe(int id, String name, int rating, Integer... reviews) {
        return new Cafe(id, name, "Минск", rating, Arrays.asList(reviews));
    }
}
