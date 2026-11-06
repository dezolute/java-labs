package org.example;

public class TShirt extends Clothing {
    public TShirt(String brand, Size size, String color) {
        super(brand, size, color);
    }

    @Override
    public String getSeason() {
        return "Spring/Summer";
    }
}
