package org.example.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class App 
{
    public static void main( String[] args )
    {
        Map<String, Plant>  map = new HashMap<>();

        map.put("Tree1", new Tree(1, "Tree1", 1.5, 10));
        map.put("Tree2", new Tree(2, "Tree2", 1.5, 10));
        map.put("Tree3", new Tree(3, "Tree3", 1.5, 10));
        map.put("Flower1", new Flower(1, "Flower1", 1.5, "Yellow"));
        map.put("Flower2", new Flower(2, "Flower2", 1.5, "Green"));
        map.put("Flower3", new Flower(3, "Flower3", 1.5, "Skyblue"));
        map.put("Shrub1", new Shrub(1, "Shrub1", 1.5, 5));
        map.put("Shrub2", new Shrub(2, "Shrub2", 1.5, 5));

        for (Map.Entry<String, Plant> entry : map.entrySet()) {
            String key = entry.getKey();
            Plant value = entry.getValue();
            System.out.println(key + " = " + value);

        }

        Iterator<Map.Entry<String, Plant>> iterator = map.entrySet().iterator();

        while(iterator.hasNext()) {
            Map.Entry<String, Plant> entry = iterator.next();
            String key = entry.getKey();
            Plant value = entry.getValue();
            System.out.println(key + " = " + value);

        }
    }
}
