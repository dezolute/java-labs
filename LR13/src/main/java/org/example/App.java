package org.example;

import java.util.ArrayList;
import java.util.List;


public class App 
{
    public static void main( String[] args )
    {
        List<Game> arr = new ArrayList<>();
        arr.add(new Game("title1", "genre1", 1, 1.5, 2021));
        arr.add(new Game("title2", "genre2", 2, 2.5, 2022));
        arr.add(new Game("title3", "genre3", 3, 3.5, 2023));
        arr.add(new Game("title4", "genre4", 4, 4.5, 2024));
        arr.add(new Game("title5", "genre5", 5, 5.5, 2027));

        GameShop gameShop = new GameShop(arr);
        System.out.println("Current");
        gameShop.current();
        System.out.println("Next");
        gameShop.next();
        gameShop.next();
        gameShop.next();
        System.out.println("Previous");
        gameShop.previous();
        System.out.println("Purchase");
        Game game = gameShop.purchase();

        try { gameShop.getByTitle("1234"); }
        catch (GameNotFoundException e) { System.out.println(e.getMessage()); }

        try {
            Game ttt = gameShop.getByTitle("title5");
            ttt.purchase();
        }
        catch (PurchaseException e) { System.out.println(e.getMessage()); }

        try { game.updatePrice(-1); }
        catch (InvalidGameDataException e) { System.out.println(e.getMessage()); }
    }
}
