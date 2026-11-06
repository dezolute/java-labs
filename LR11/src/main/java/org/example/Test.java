package org.example;

public class Test extends Clothing {
    public Test(String brand, String size, String color) {
        super(brand, size, color);
    }

    @Override
    public void wear() {
        System.out.println("Test");
    }

    public void wear(double d) {
        System.out.println(d);
    }

    public void wear(String[] arr) {
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
