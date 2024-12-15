package day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Main {
    
    public static final boolean testMode = false;
    
    public static void main(String[] args) {
        String[][] map = initMap();
        printMap(map);
        partOne(map);
    }
    
    public static void partOne(String[][] map) {
        ArrayList<String> results = new ArrayList<>();
        HashMap<String, ArrayList<Pair>> fieldCoordinatesMap = new HashMap<>();
        HashMap<String, ArrayList<Pair>> antiNodeCoordinatesMap = new HashMap<>();
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if(!Objects.equals(map[y][x], ".")) {
                    ArrayList<Pair> list;
                    if(fieldCoordinatesMap.containsKey(map[y][x])) {
                        list = fieldCoordinatesMap.get(map[y][x]);
                    } else {
                        list = new ArrayList<>();
                    }
                    list.add(new Pair(x,y));
                    fieldCoordinatesMap.put(map[y][x], list);
                }
            }
        }
        for (HashMap.Entry<String, ArrayList<Pair>> entry : fieldCoordinatesMap.entrySet()) {
            //System.out.println(entry.getKey() + " " + entry.getValue());
            ArrayList<Pair> fieldCoordinates = entry.getValue();
            for (int i = 0; i < fieldCoordinates.size(); i++) {
                Pair currentField = fieldCoordinates.get(i);
                //System.out.println("CHECKING FOR ANTI NODE " + currentField);
                for (int j = 0; j < fieldCoordinates.size(); j++) {
                    if(i != j) {
                        Pair compareField = fieldCoordinates.get(j);
                        //System.out.println("MATCHING WITH " + compareField);
                        int dirX = currentField.getX() - compareField.getX();
                        int dirY = currentField.getY() - compareField.getY();
                        //System.out.println("DIRECTION TO CHECK X" + dirX + " Y" + dirY);
                        if(((currentField.getX() + dirX) > -1 && (currentField.getX() + dirX) <= map[0].length) &&
                            ((currentField.getY() + dirY) > -1 && (currentField.getY() + dirY) <= map.length)
                        ) {
                            //System.out.println("POSSIBLE");
                            String field = Integer.toString(currentField.getX() + dirX).concat(Integer.toString(currentField.getY() + dirY));
                            if(!results.contains(field)) {
                                results.add(field);
                                //System.out.println(field + " ADDED");
                            } else {
                                //System.out.println(field + " ALREADY ADDED");
                            }
                        }
                    }
                }
            }
        }
        System.out.println(results.size());
    }
    
    public static String[][] initMap() {
        String[][] map = new String[0][];
        String fileName = testMode ? "src/day08/test_input.txt" : "src/day08/input.txt";
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            map = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                map[i] = lines.get(i).split("");
            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return map;
    }
    
    public static void printMap(String[][] map) {
        for(String[] mapRow: map) {
            for(String field: mapRow) {
                System.out.print(field + " ");
            }
            System.out.println();
        }
    }
    
    public static List<Integer> findAll(String[] array, String element) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(element)) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}
