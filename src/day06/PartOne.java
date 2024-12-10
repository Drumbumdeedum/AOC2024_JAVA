package day06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class PartOne {
    public static void main(String[] args) {
        String fileName = "src/day06/testInput.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            LabMap map = new LabMap();
            while((line = reader.readLine()) != null) {
                ArrayList<String> mapRow = new ArrayList<>(Arrays.asList(line.split("")));
                map.addMapRow(mapRow);
                if(mapRow.contains("^")) {
                    map.setGuardPosX(mapRow.indexOf("^"));
                    map.setGuardPosY(map.getMapGrid().size() - 1);
                }
            }
            map.setWidth(map.getMapGrid().getFirst().size());
            map.setHeight(map.getMapGrid().size());
            while(map.isGuardOnMap()) {
                switch(map.getGuardDirection()) {
                    case UP -> {
                        if(map.moveUpPossible()) {
                            if(map.obstacleUp()) {
                                map.rotateGuard();
                            } else {
                                map.moveGuardUp();
                            }
                        } else {
                            map.moveGuardOffMap();
                            map.setGuardOnMap(false);
                        }
                    }
                    case DOWN -> {
                        if(map.moveDownPossible()) {
                            if(map.obstacleDown()) {
                                map.rotateGuard();
                            } else {
                                map.moveGuardDown();
                            }
                        } else {
                            map.moveGuardOffMap();
                            map.setGuardOnMap(false);
                        }
                    }
                    case LEFT -> {
                        if(map.moveLeftPossible()) {
                            if(map.obstacleLeft()) {
                                map.rotateGuard();
                            } else {
                                map.moveGuardLeft();
                            }
                        } else {
                            map.moveGuardOffMap();
                            map.setGuardOnMap(false);
                        }
                    }
                    case RIGHT -> {
                        if(map.moveRightPossible()) {
                            if(map.obstacleRight()) {
                                map.rotateGuard();
                            } else {
                                map.moveGuardRight();
                            }
                        } else {
                            map.moveGuardOffMap();
                            map.setGuardOnMap(false);
                        }
                    }
                }
                map.printMap();
            }
            System.out.println(map.countUniqueMoves());
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
