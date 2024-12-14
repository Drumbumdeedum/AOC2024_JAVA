package day06.altTake;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LabMap {
    public static final boolean testMode = false;
    public static void main(String[] args) {
        String[][] mapGrid = initMap();
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
        HashMap<Integer, ArrayList<Integer>> visitedFields = new HashMap<>();
        while(true) {
            printMap(mapGrid, visitedFields, guardX, guardY, guardDir);
            int visitedField = Integer.parseInt(String.valueOf(guardX).concat(String.valueOf(guardY)));
            ArrayList<Integer> visitedFrom;
            if (visitedFields.containsKey(visitedField)) {
                visitedFrom = visitedFields.get(visitedField);
            } else {
                visitedFrom = new ArrayList<>();
            }
            visitedFrom.add(guardDir);
            visitedFields.put(visitedField, visitedFrom);
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
        printMap(mapGrid, visitedFields, -1, -1, 0);
        System.out.println("UNIQUE FIELDS VISITED: " + visitedFields.size());
    }
    
    public static String[][] initMap() {
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
    
    public static void printMap(String[][] mapGrid, HashMap<Integer, ArrayList<Integer>> visitedFields, int guardX, int guardY, int guardDir) {
        if (testMode) {
            System.out.println();
            for (int y = 0; y < mapGrid.length; y++) {
                for (int x = 0; x < mapGrid[y].length; x++) {
                    if(guardX == x && guardY == y) {
                        System.out.print(getGuardDirString(guardDir));
                    } else if(visitedFields.containsKey(Integer.parseInt(String.valueOf(x).concat(String.valueOf(y))))) {
                        ArrayList<Integer> visitedFrom = visitedFields.get(Integer.parseInt(String.valueOf(x).concat(String.valueOf(y))));
                        if((visitedFrom.contains(0) || visitedFrom.contains(2)) && (visitedFrom.contains(1) || visitedFrom.contains(3))) {
                            System.out.print("+ ");
                        } else if(visitedFrom.contains(0) || visitedFrom.contains(2)) {
                            System.out.print("| ");
                        }else if(visitedFrom.contains(1) || visitedFrom.contains(3)) {
                            System.out.print("- ");
                        } else {
                            System.out.print("X ");
                        }
                    } else {
                        System.out.print(mapGrid[y][x] + " ");
                    }
                }
                System.out.println();
            }
        }
    }
    
    public static String getGuardDirString(int guardDir) {
        return switch(guardDir) {
            case 0 -> "^ ";
            case 1 -> "> ";
            case 2 -> "v ";
            case 3 -> "< ";
            default -> "  ";
        };
    }
}
