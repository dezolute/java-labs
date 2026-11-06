package org.example.task1;

public class Tree extends Plant {
    private int crownDiameter;

    public Tree(int id, String name, double height, int crownDiameter) {
        super(id, name, height);
        this.crownDiameter = crownDiameter;
    }

    public int getCrownDiameter() {
        return crownDiameter;
    }

    public void setCrownDiameter(int crownDiameter) {
        this.crownDiameter = crownDiameter;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return String.format("%s, crownDiameter='%d')", s, crownDiameter);
    }
}
