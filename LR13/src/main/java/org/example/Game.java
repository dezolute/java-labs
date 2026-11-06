package org.example;

import java.time.LocalDate;

public class Game {
    private String title;
    private String genre;
    private int rating;
    private double price;
    private int releaseYear;

    public Game(String title, String genre, int rating, double price, int releaseYear) {
        setTitle(title);
        setGenre(genre);
        setPrice(price);
        setRating(rating);
        setReleaseYear(releaseYear);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) throws InvalidGameDataException {
        if (rating < 0 || rating > 10) {
            throw new InvalidGameDataException("Рейтинг должен находиться в диапазоне от 0 до 10");
        }
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    protected void setPrice(double price) throws InvalidGameDataException {
        if (price < 0.0) {
            this.price = 0.0;
            throw new InvalidGameDataException("Цена не может быть отрицательной");
        }
        this.price = price;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Game purchase() throws PurchaseException {
        if (LocalDate.now().getYear() < releaseYear) {
            throw new PurchaseException();
        }
        return this;
    }

    public void updatePrice(double newPrice) {
        setPrice(newPrice);
    }

    @Override
    public String toString() {
        return "Game{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", rating=" + rating +
                ", price=" + price +
                ", releaseYear=" + releaseYear +
                '}';
    }
}
