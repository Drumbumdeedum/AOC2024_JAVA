package day06.altTake;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class LabMap {
    public static void main(String[] args) {
        String[][] mapGrid = initMap(false);
        int guardX = 0;
        int guardY = 0;
        boolean guardFound = false;
        while(!guardFound) {
            for (guardY = 0; guardY < mapGrid.length; guardY++) {
                for (guardX = 0; guardX < mapGrid.length; guardX++) {
                    if(mapGrid[guardY][guardX].equals("^")) {
                        guardFound = true;
                        break;
                    }
                }
                if (guardFound) {
                    break;
                }
            }
        }
        int[][] directions = {
                // UP
                {0, -1},
                // RIGHT
                {1, 0},
                // DOWN
                {0, 1},
                // LEFT
                {-1, 0}
        };
        int guardDir = 0;
        int nextX;
        int nextY;
        HashMap<Integer, Integer> visitedFields = new HashMap<>();
        while(true) {
            //printMap(mapGrid, visitedFields);
            int visitedField = Integer.parseInt(String.valueOf(guardX).concat(String.valueOf(guardY)));
            if (visitedFields.containsKey(visitedField)) {
                visitedFields.put(visitedField, visitedFields.get(visitedField) + 1);
            } else {
                visitedFields.put(visitedField, 1);
            }
            visitedFields.put(visitedField, visitedFields.get(visitedField) + 1);
            nextX = guardX + directions[guardDir][0];
            nextY = guardY + directions[guardDir][1];
            if(nextX == -1 || nextY == -1 || nextX == mapGrid[0].length || nextY == mapGrid.length) break;
            if(mapGrid[nextY][nextX].equals("#")) {
                guardDir = (guardDir + 1) % 4;
            } else {
                guardX = nextX;
                guardY = nextY;
            }
        }
        //printMap(mapGrid, visitedFields);
        System.out.println(visitedFields.size());
    }
    
    public static String[][] initMap(boolean testMode) {
        String filePath = testMode ? "src/day06/testInput.txt" : "src/day06/input.txt";
        String[][] result = new String[0][];
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            result = new String[lines.size()][];
            for (int i = 0; i < lines.size(); i++) {
                result[i] = lines.get(i).split(""); // Split each line into characters
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }
    
    public static void printMap(String[][] mapGrid, HashMap<Integer, Integer> visitedFields) {
        System.out.println();
        for (int y = 0; y < mapGrid.length; y++) {
            for (int x = 0; x < mapGrid[y].length; x++) {
                if(visitedFields.containsKey(Integer.parseInt(String.valueOf(x).concat(String.valueOf(y))))) {
                    System.out.print("X ");
                } else {
                    System.out.print(mapGrid[y][x] + " ");
                }
            }
            System.out.println();
        }
    }
}
