package org.example;

public class TShirt extends Clothing {
    private String sleeveType;

    public TShirt(String brand, String size, String color,
                  String sleeveType) {
        super(brand, size, color);
        this.sleeveType = sleeveType;
    }

    @Override
    public String getSeason() {
        return "Лето";
    }

    public void wear(String occasion, boolean tuckedIn) {
        System.out.println("Футболка для " + occasion +
                (tuckedIn ? ", заправленная" : ", навыпуск"));
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Тип рукава: " + sleeveType);
    }
}
