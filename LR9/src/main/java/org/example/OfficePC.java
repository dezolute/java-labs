package org.example;

public class OfficePC extends Computer {
    private String monitorSize;

    public OfficePC(String processor, int ram, int storage, String monitorSize) {
        super(processor, ram, storage);
        this.monitorSize = monitorSize;
    }

    @Override
    public void getPerformance() {
        System.out.println("Средняя");
    }

    @Override
    public void getPurpose() {
        System.out.println("Для офисных работ");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("monitorSize=%s]%n", monitorSize);
    }
}
