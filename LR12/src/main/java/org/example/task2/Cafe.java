package org.example.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Cafe {
    private final int id;
    private final String name;
    private final String location;
    private int rating;
    private final List<Integer> visitorReviews;

    public Cafe(int id, String name, String location, int rating, List<Integer> visitorReviews) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Название кафе не должно быть null");
        this.location = Objects.requireNonNull(location, "Адрес не должен быть null");
        this.rating = rating;
        this.visitorReviews = new ArrayList<>(
                Objects.requireNonNull(visitorReviews, "Список отзывов не должен быть null"));
        for (Integer review : this.visitorReviews) {
            Objects.requireNonNull(review, "Оценка не должна быть null");
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<Integer> getVisitorReviews() {
        return Collections.unmodifiableList(visitorReviews);
    }

    // При отсутствии отзывов средняя оценка считается равной нулю.
    public double getAverageReview() {
        if (visitorReviews.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (int review : visitorReviews) {
            sum += review;
        }
        return sum / visitorReviews.size();
    }

    @Override
    public String toString() {
        return String.format("Cafe(id=%d, name='%s', location='%s', rating=%d, averageReview=%.2f)",
                id, name, location, rating, getAverageReview());
    }
}
