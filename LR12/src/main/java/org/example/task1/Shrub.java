package org.example.task1;

public class Shrub extends Plant {
    private int branchCount;

    public Shrub(int id, String name, double height, int branchCount) {
        super(id, name, height);
        this.branchCount = branchCount;
    }

    public int getBranchCount() {
        return branchCount;
    }

    public void setBranchCount(int branchCount) {
        this.branchCount = branchCount;
    }

    @Override
    public String toString() {
        String s = super.toString();
        return String.format("%s, branchCount='%d')", s, branchCount);
    }
}
