package org.example;


public class App 
{
    public static void main( String[] args )
    {
        Clothing clothing = new Clothing( "Basic", "M", "Черный");

        Jacket jacket = new Jacket("Nike", "L", "Синий", true);

        TShirt tShirt = new TShirt( "Adidas", "M", "Белый", "Короткий");

        System.out.println("Перегрузка методов");

        clothing.wear();
        clothing.wear("работу");
        clothing.wear(15);

        jacket.wear(true);
        tShirt.wear("прогулки", false);

        System.out.println("\nПереопределение методов");

        System.out.println(clothing.getSeason());
        System.out.println(jacket.getSeason());
        System.out.println(tShirt.getSeason());

        System.out.println("\nДинамический полиморфизм");

        Clothing[] clothes = {
                clothing, jacket, tShirt
        };

        for (Clothing item : clothes) {
            item.displayInfo();
            System.out.println("Сезон: " + item.getSeason());
            System.out.println();
        }

        System.out.println("Статический полиморфизм");

        Clothing item = new Jacket( "Puma", "XL", "Черный", false);

        item.wear();
        item.wear("праздник");
        item.wear(-5);
    }
}
