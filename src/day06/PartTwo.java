package day06;

import day06.partTwo.GameMap;
import day06.partTwo.Guard;
import day06.partTwo.MapField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PartTwo {
    public static void main(String[] args) {
        String fileName = "src/day06/testInput.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            GameMap map = new GameMap();
            while((line = reader.readLine()) != null) {
                String[] entries = line.split("");
                ArrayList<MapField> mapRow = new ArrayList<>();
                for (int i = 0; i < entries.length; i++) {
                    MapField field = new MapField();
                    field.initTypeFromString(entries[i]);
                    field.setPosX(i);
                    field.setPosY(map.getMapFields().size());
                    mapRow.add(field);
                    if(entries[i].equals("^")) {
                        Guard guard = new Guard();
                        guard.setPosX(i);
                        guard.setPosY(map.getMapFields().size());
                        map.setGuard(guard);
                    }
                }
                map.addMapRow(mapRow);
            }
            map.setWidth(map.getMapFields().size());
            map.setHeight(map.getMapFields().getFirst().size());
            
            while(map.getGuard().guardOnMap(map.getWidth(), map.getHeight())) {
                map.getGuard().moveGuard(map.getWidth(), map.getHeight());
                System.out.println(map);
            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
