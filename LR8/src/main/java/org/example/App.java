package org.example;

public class App 
{
    public static void main( String[] args )
    {
        Wardrobe wardrobe = new Wardrobe();
        wardrobe.addJacket("Gucci", Size.S, "White");
        wardrobe.addJacket("Stone Island", Size.XL, "Black");
        wardrobe.addJacket("Chanel", Size.M, "Yellow");
        wardrobe.addTShirt("Prada", Size.L, "Gray");
        wardrobe.addTShirt("Zara", Size.XS, "Light-blue");

        System.out.println(wardrobe);

        for(Clothing c : wardrobe.getClothing()) {
            c.displayInfo();
            System.out.println(c.getSeason());
        }
    }

}
