package org.example;

public class GamingPC extends Computer{
    private String videoCard;

    public GamingPC(String processor, int ram, int storage, String videoCard) {
        super(processor, ram, storage);
        this.videoCard = videoCard;
    }

    @Override
    public void getPerformance() {
        System.out.println("Высокая");
    }

    @Override
    public void getPurpose() {
        System.out.println("Для игр");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("videoCard=%s]%n", videoCard);
    }
}
