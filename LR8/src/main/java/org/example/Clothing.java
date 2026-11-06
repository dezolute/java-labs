package org.example;

public class Clothing {
    private String brand;
    private Size size;
    private String color;

    public Clothing(String brand, Size size, String color) {
        this.brand = brand;
        this.size = size;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return String.format("%s[brand=%s, size=%s, color=%s]%n",
            this.getClass().toString().split("\\.")[2],
            brand, size.toString(), color
        );
    }
    public String getSeason() {
        return "All seasons";
    }

    public void displayInfo() {
        System.out.print(this);
    }
}
