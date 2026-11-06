package org.example;

public abstract class Computer {
    private String processor;
    private int ram;
    private int storage;

    public Computer(String processor, int ram, int storage) {
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
    }

    public abstract void getPerformance();
    public abstract void getPurpose();

    public void displayInfo() {
        System.out.printf("%s[processor=%s, ram=%d, storage=%d, ",
                this.getClass().getName().split("\\.")[2],
                processor, ram, storage
        );
    }
}
