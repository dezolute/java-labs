package org.example;


public class App 
{
    public static void main(String[] args) {
        Building b = new Building("123", 123, 123.123);
        Building c = new Building("123", 123, 123.123, 123);
        b.setAddress("321");
        System.out.println(b.getAddress());
    }
}
