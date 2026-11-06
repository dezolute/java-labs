package org.example;

import java.util.ArrayList;
import java.util.List;

public class Wardrobe {
    private List<Clothing> clothing;

    public Wardrobe() {
        clothing = new ArrayList<>();
    }

    public List<Clothing> getClothing() { return clothing; }

    public void addTShirt(String brand, Size size, String color) {
        clothing.add(new TShirt(brand, size, color));
    }

    public void addJacket(String brand, Size size, String color) {
        clothing.add(new Jacket(brand, size, color));
    }

    @Override
    public String toString() {
        StringBuilder stringBuffer = new StringBuilder();
        for (Clothing c : clothing) {
            stringBuffer.append(c.toString());
        }
        return stringBuffer.toString();
    }
}
