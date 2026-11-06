package org.example;

public class Jacket extends Clothing {
    private boolean hasHood;

    public Jacket(String brand, String size, String color,
                  boolean hasHood) {
        super(brand, size, color);
        this.hasHood = hasHood;
    }

    @Override
    public String getSeason() {
        return "Зима";
    }

    public void wear(boolean zipped) {
        if (zipped) {
            System.out.println("Надеваю куртку на молнии");
        } else {
            System.out.println("Надеваю куртку расстегнутой");
        }
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Есть капюшон: " +
                (hasHood ? "Да" : "Нет"));
    }
}
