package org.example;

public class App {
    public static void main(String[] args) {
        City[] cities = {
            new City("Гродно", "Беларусь", 1000),
            new City("Минск", "Беларусь", 2000),
            new City("Варшава", "Польша", 3000),
            new City("Гданьск", "Польша", 4000),
        };

        System.out.printf("Средняя численость по городам: %d", City.getAveragePopulation());
        City.print(cities[1]);
    }
}
