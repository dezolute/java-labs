package org.example;

public class Jacket implements Washable, Wearable{
    public String brand;
    public int size;
    public String material;

    public Jacket(String brand, int size, String material) {
        this.brand = brand;
        this.size = size;
        this.material = material;
    }

    @Override
    public void wash() {
        System.out.printf("Jacket(%s) постиран%n", brand);
    }

    @Override
    public String getMaterial() {
        return material;
    }

    @Override
    public void wear() {
        System.out.printf("Jacket(%s) одета%n", brand);
    }

    @Override
    public int getSize() {
        return size;
    }
}
