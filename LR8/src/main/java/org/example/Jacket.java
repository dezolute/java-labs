package org.example;

public class Jacket extends Clothing {
    public Jacket(String brand, Size size, String color) {
        super(brand, size, color);
    }

    @Override
    public String getSeason() {
        return "Autumn/Winter";
    }

    @Override
    public void displayInfo() {
        System.out.println("From Jacket");
        super.displayInfo();
    }
}
