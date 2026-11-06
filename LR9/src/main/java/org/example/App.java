package org.example;

public class App 
{
    public static void main( String[] args )
    {
        Computer[] arr = {
            new GamingPC("i5", 32, 1024, "RTX 4090"),
            new OfficePC("Xeon", 4, 2048, "1920x1080"),
            new GamingPC("i7", 16, 512, "RTX 3050"),
            new OfficePC("Celeron", 2, 1024, "1440x1000"),
            new GamingPC("i9", 64, 2048, "RTX 5090")
        };

        for (Computer c : arr) {
            c.displayInfo();
            c.getPerformance();
        }
    }
}
