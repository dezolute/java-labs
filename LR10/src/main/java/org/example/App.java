package org.example;


public class App 
{
    public static void main(String[] args) {
        Wearable[] wearables = {
            new Jacket("Gucci", 32, "Кожа"),
            new TShirt("Lacoste", 48, "Лаймово-циановый"),
            new Jacket("Prada", 15, "Кашимир"),
            new TShirt("Polo", 24, "Небесно голубой"),
            new Jacket("Puma", 28, "Мех"),
        };

        Washable[] washables = {
            new Jacket("Gucci", 32, "Кожа"),
            new Jacket("Prada", 15, "Кашимир"),
            new Jacket("Puma", 28, "Мех"),
        };

        for (Washable c : washables) {
            c.wash();
        }
        for (Wearable c : wearables) {
            c.wear();
        }
    }
}
