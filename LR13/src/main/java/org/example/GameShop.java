package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameShop {
    private List<Game> catalog;
    private Game selected;
    private int index = 0;

    public GameShop(List<Game> catalog) {
        this.catalog = catalog;
    }

    public List<Game> getCatalog() {
        return catalog;
    }

    public Game getByTitle(String title) throws GameNotFoundException {
        Game game = catalog.stream().filter(el -> el.getTitle().equals(title)).findFirst().orElse(null);
        if (game == null) {
            throw new GameNotFoundException();
        }
        return game;
    }

    public void next() {
        index++;
        if (index > catalog.size() - 1) {
            index = 0;
        }
        selected = catalog.get(index);
        System.out.println(selected);
    }

    public void previous() {
        index--;
        if (index < 0) {
            index = catalog.size() - 1;
        }
        selected = catalog.get(index);
        System.out.println(selected);
    }

    public void current() {
        System.out.println(selected);
    }

    public Game purchase() {
        return selected.purchase();
    }
}
