package day06;

import day06.partTwo.FieldType;
import day06.partTwo.GameMap;
import day06.partTwo.Guard;
import day06.partTwo.MapField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static day06.partTwo.FieldType.*;
import static day06.partTwo.GuardDirection.*;

public class PartTwo {
    public static void main(String[] args) {
        int uniqueFieldsVisited = 0;
        int totalMoves;
        int resultMoves = 5760;
        //int resultMoves = 45;
        int attempt = 2665;
        boolean obstacleAdded = false;
        int loopCount = 612;
        int FAKAPPCOUNT = 3;
        GameMap map;
        //GameMap map = initMap();
        while (attempt <= resultMoves) {
            map = initMap();
            obstacleAdded = false;
            boolean loopFound = false;
            totalMoves = 0;
            uniqueFieldsVisited = 0;
            System.out.println("ATTEMPT: " + attempt);
            
            //while(map.getGuard().guardOnMap(map.getWidth(), map.getHeight())) {
            while(map.getGuard().guardOnMap(map.getWidth(), map.getHeight()) && !loopFound) {
                if(map.hasNextField()) {
                    if(totalMoves == attempt && !obstacleAdded) {
                        map.updateMapField(map.nextField().getPosX(), map.nextField().getPosY(), OBSTACLE, false);
                        obstacleAdded = true;
                    }
                    switch (map.currentField().getType()) {
                        case EMPTY -> {
                            if(map.nextField().getType() == BLOCK || map.nextField().getType() == OBSTACLE) {
                                map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), FieldType.CROSS, true);
                            } else if(map.getGuard().getGuardDirection() == UP || map.getGuard().getGuardDirection() == DOWN) {
                                map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), PATH_Y, true, map.getGuard().getGuardDirection());
                            } else if(map.getGuard().getGuardDirection() == LEFT || map.getGuard().getGuardDirection() == RIGHT) {
                                map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), FieldType.PATH_X, true, map.getGuard().getGuardDirection());
                            }
                        }
                        case PATH_X -> {
                            if(map.currentField().isVisited()) {
                                if(map.getGuard().getGuardDirection() == UP || map.getGuard().getGuardDirection() == DOWN) {
                                    map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), FieldType.CROSS, true);
                                }
                            }
                        }
                        case PATH_Y -> {
                            if(map.currentField().isVisited()) {
                                if(map.getGuard().getGuardDirection() == LEFT || map.getGuard().getGuardDirection() == RIGHT) {
                                    map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), FieldType.CROSS, true);
                                }
                            }
                        }
                    }
                    switch(map.nextField().getType()) {
                        case EMPTY, PATH_X, PATH_Y, CROSS -> {
                            if(!map.nextField().isVisited()) {
                                uniqueFieldsVisited++;
                            } else {
                                if(
                                    (map.nextField().getEnteredWithDirection() == RIGHT && map.getGuard().getGuardDirection() == RIGHT) ||
                                    (map.nextField().getEnteredWithDirection() == LEFT && map.getGuard().getGuardDirection() == LEFT) ||
                                    (map.nextField().getEnteredWithDirection() == UP && map.getGuard().getGuardDirection() == UP) ||
                                    (map.nextField().getEnteredWithDirection() == DOWN && map.getGuard().getGuardDirection() == DOWN)
                                ) {
                                    //System.out.println("LOOP FOUND!!!");
                                    loopFound = true;
                                    loopCount++;
                                    System.out.println("LOOPS FOUND: " + loopCount);
                                    //System.out.println(map);
                                    break;
                                }
                            }
                            //System.out.println("MOVES: " + totalMoves);
                            totalMoves++;
                            map.getGuard().moveGuard(map.getWidth(), map.getHeight());
                        }
                        case BLOCK, OBSTACLE -> map.getGuard().turnGuardRight();
                    }
                } else {
                    switch (map.getGuard().getGuardDirection()) {
                        case UP, DOWN ->  map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), PATH_Y, true);
                        case LEFT, RIGHT ->  map.updateMapField(map.getGuard().getPosX(), map.getGuard().getPosY(), FieldType.PATH_X, true);
                    }
                    uniqueFieldsVisited++;
                    totalMoves++;
                    map.getGuard().moveGuard(map.getWidth(), map.getHeight());
                }
                //System.out.println(map);
            }
            //System.out.println("COMPLETED IN " + totalMoves + " TOTAL MOVES, " + uniqueFieldsVisited + " UNIQUE FIELDS VISITED");
            //System.out.println("---------------------------------------------------");
            attempt++;
        }
    }
    
    private static GameMap initMap() {
        GameMap map = new GameMap();
        String fileName = "src/day06/input.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            ArrayList<ArrayList<MapField>> newMap = new ArrayList<>();
            while((line = reader.readLine()) != null) {
                String[] entries = line.split("");
                ArrayList<MapField> mapRow = new ArrayList<>();
                for (int i = 0; i < entries.length; i++) {
                    MapField field = new MapField();
                    field.initTypeFromString(entries[i]);
                    field.setPosX(i);
                    field.setPosY(newMap.size());
                    mapRow.add(field);
                    if(entries[i].equals("^")) {
                        Guard guard = new Guard();
                        guard.setPosX(i);
                        guard.setPosY(newMap.size());
                        map.setGuard(guard);
                    }
                }
                newMap.add(mapRow);
            }
            map.setMapFields(newMap);
            map.setWidth(map.getMapFields().size());
            map.setHeight(map.getMapFields().getFirst().size());
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
        return map;
    }
}
