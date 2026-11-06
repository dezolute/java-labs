package org.example;

public class City {
    private String name;
    private String country;
    private long population;
    private static int totalCities = 0;
    private static long totalPopulation = 0;

    public City(String name, String country, long population) {
        this.name = name;
        this.country = country;
        this.population = population;
        totalCities++;
        totalPopulation += population;
    }

    public String getName() { return name; }
    public String getCountry() { return country; }
    public long getPopulation() { return population; }

    public static long getAveragePopulation() {
        return totalPopulation / totalCities;
    }

    public static void print(City c) {
        System.out.println(c.getCountry());
        System.out.println(c.getPopulation());
        System.out.println(c.getName());
        System.out.println(City.getAveragePopulation());
    }
}
