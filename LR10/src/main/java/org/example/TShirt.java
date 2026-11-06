package org.example;


public class TShirt implements Wearable {
    public String brand;
    public int size;
    public String color;

    public TShirt(String brand, int size, String color) {
        this.brand = brand;
        this.size = size;
        this.color = color;
    }

    @Override
    public void wear() {
        System.out.printf("TShirt(%s) одета%n", brand);
    }

    @Override
    public int getSize() {
        return size;
    }
}
