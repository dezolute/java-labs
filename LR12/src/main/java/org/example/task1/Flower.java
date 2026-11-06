package org.example.task1;

public class Flower extends Plant {
    private String bloomColor;


    public Flower(int id, String name, double height, String bloomColor) {
        super(id, name, height);
        this.bloomColor = bloomColor;
    }

    public String getBloomColor() {
        return bloomColor;
    }

    public void setBloomColor(String bloomColor) {
        this.bloomColor = bloomColor;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return String.format("%s, bloomColor='%s')", s, bloomColor);
    }
}
