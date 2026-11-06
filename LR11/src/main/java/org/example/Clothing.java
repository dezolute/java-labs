package org.example;

public class Clothing {
    protected String brand;
    protected String size;
    protected String color;

    public Clothing(String brand, String size, String color) {
        this.brand = brand;
        this.size = size;
        this.color = color;
    }

    public void wear() {
        System.out.println("Одеваю");
    }

    public void wear(String occasion) {
        System.out.println("Одеваю для " + occasion);
    }

    public void wear(int temperature) {
        System.out.println("Одеваю при " + temperature + "°C");
    }

    public String getSeason() {
        return "Всесезонная";
    }

    public void displayInfo() {
        System.out.println("Бренд: " + brand);
        System.out.println("Размер: " + size);
        System.out.println("Цвет: " + color);
    }
}
