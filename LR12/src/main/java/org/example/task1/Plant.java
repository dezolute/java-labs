package org.example.task1;

public class Plant {
    private int id;
    private String name;
    private double height;

    public Plant(int id, String name, double height) {
        this.id = id;
        this.name = name;
        this.height = height;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("%s(name='%s', id='%d', height='%.2f'",
            this.getClass().getSimpleName(), name, id, height
        );
    }
}
