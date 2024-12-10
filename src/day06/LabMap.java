package day06;

import java.util.ArrayList;

public class LabMap {
    private int width;
    private int height;
    private Integer guardPosY;
    private Integer guardPosX;
    private GuardDirection guardDirection = GuardDirection.UP;
    private boolean guardOnMap = true;
    private ArrayList<ArrayList<String>> mapGrid = new ArrayList<>();
    
    public int getWidth() {
        return width;
    }
    
    public void setWidth(int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public Integer getGuardPosY() {
        return guardPosY;
    }
    
    public void setGuardPosY(Integer guardPosY) {
        this.guardPosY = guardPosY;
    }
    
    public Integer getGuardPosX() {
        return guardPosX;
    }
    
    public void setGuardPosX(Integer guardPosX) {
        this.guardPosX = guardPosX;
    }
    
    public GuardDirection getGuardDirection() {
        return guardDirection;
    }
    
    public void setGuardDirection(GuardDirection guardDirection) {
        this.guardDirection = guardDirection;
    }
    
    public ArrayList<ArrayList<String>> getMapGrid() {
        return mapGrid;
    }
    
    public void setMapGrid(ArrayList<ArrayList<String>> mapGrid) {
        this.mapGrid = mapGrid;
    }
    
    public void addMapRow(ArrayList<String> mapRow) {
        this.mapGrid.add(mapRow);
    }
    
    public void setMapField(int x, int y, String value) {
        ArrayList<String> currentRow = this.mapGrid.get(y);
        currentRow.set(x, value);
        this.mapGrid.set(y, currentRow);
    }
    
    public String getMapField(int x, int y) {
        return this.mapGrid.get(y).get(x);
    }
    
    public void printMap() {
        for (ArrayList<String> mapRow : this.mapGrid) {
            for (String mapField : mapRow) {
                System.out.print(mapField);
            }
            System.out.println();
        }
    }
    
    public boolean isGuardOnMap() {
        return guardOnMap;
    }
    
    public void setGuardOnMap(boolean guardOnMap) {
        this.guardOnMap = guardOnMap;
    }
    
    public enum GuardDirection {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
    
    public String getDirectionString() {
        return switch (getGuardDirection()) {
            case GuardDirection.UP -> "^";
            case GuardDirection.DOWN -> "∨";
            case GuardDirection.RIGHT -> ">";
            case GuardDirection.LEFT -> "<";
        };
    }
    
    public boolean moveUpPossible() {
        return getGuardPosY() != 0;
    }
    
    public boolean moveDownPossible() {
        return getGuardPosY() != getHeight() - 1;
    }
    
    public boolean moveRightPossible() {
        return getGuardPosX() != getWidth() - 1;
    }
    
    public boolean moveLeftPossible() {
        return getGuardPosX() != 0;
    }
    
    public boolean obstacleUp() {
        return getMapField(getGuardPosX(), getGuardPosY() - 1).equals("#");
    }
    
    public boolean obstacleDown() {
        return getMapField(getGuardPosX(), getGuardPosY() + 1).equals("#");
    }
    
    public boolean obstacleLeft() {
        return getMapField(getGuardPosX() - 1, getGuardPosY()).equals("#");
    }
    
    public boolean obstacleRight() {
        return getMapField(getGuardPosX() + 1, getGuardPosY()).equals("#");
    }
    
    public void moveGuardUp() {
        setMapField(getGuardPosX(), getGuardPosY(), "X");
        setMapField(getGuardPosX(), getGuardPosY() - 1, "^");
        setGuardPosY(getGuardPosY() - 1);
    }
    
    public void moveGuardUpDrawPath() {
        if(getMapField(getGuardPosX() + 1, getGuardPosY()).equals("-") || getMapField(getGuardPosX() + 1, getGuardPosY()).equals("+")) {
            setMapField(getGuardPosX(), getGuardPosY(), "+");
        } else {
            setMapField(getGuardPosX(), getGuardPosY(), "|");
        }
        setMapField(getGuardPosX(), getGuardPosY() - 1, "^");
        setGuardPosY(getGuardPosY() - 1);
    }
    
    public void moveGuardDown() {
        setMapField(getGuardPosX(), getGuardPosY(), "X");
        setMapField(getGuardPosX(), getGuardPosY() + 1, "∨");
        setGuardPosY(getGuardPosY() + 1);
    }
    
    public void moveGuardDownDrawPath() {
        if(getMapField(getGuardPosX() - 1, getGuardPosY()).equals("-") || getMapField(getGuardPosX() - 1, getGuardPosY()).equals("+")) {
            setMapField(getGuardPosX(), getGuardPosY(), "+");
        } else {
            setMapField(getGuardPosX(), getGuardPosY(), "|");
        }
        setMapField(getGuardPosX(), getGuardPosY() + 1, "∨");
        setGuardPosY(getGuardPosY() + 1);
    }
    
    public void moveGuardRight() {
        setMapField(getGuardPosX(), getGuardPosY(), "X");
        setMapField(getGuardPosX() + 1, getGuardPosY(), ">");
        setGuardPosX(getGuardPosX() + 1);
    }
    
    public void moveGuardRightDrawPath() {
        if(getMapField(getGuardPosX(), getGuardPosY() + 1).equals("|") || getMapField(getGuardPosX(), getGuardPosY() + 1).equals("+")) {
            setMapField(getGuardPosX(), getGuardPosY(), "+");
        } else {
            setMapField(getGuardPosX(), getGuardPosY(), "-");
        }
        setMapField(getGuardPosX() + 1, getGuardPosY(), ">");
        setGuardPosX(getGuardPosX() + 1);
    }
    
    public void moveGuardLeft() {
        setMapField(getGuardPosX(), getGuardPosY(), "X");
        setMapField(getGuardPosX() - 1, getGuardPosY(), "<");
        setGuardPosX(getGuardPosX() - 1);
    }
    
    public void moveGuardLeftDrawPath() {
        if(getMapField(getGuardPosX(), getGuardPosY() - 1).equals("|") || getMapField(getGuardPosX(), getGuardPosY() - 1).equals("+")) {
            setMapField(getGuardPosX(), getGuardPosY(), "+");
        } else {
            setMapField(getGuardPosX(), getGuardPosY(), "-");
        }
        setMapField(getGuardPosX() - 1, getGuardPosY(), "<");
        setGuardPosX(getGuardPosX() - 1);
    }
    
    public void moveGuardOffMap() {
        setMapField(getGuardPosX(), getGuardPosY(), "X");
        setGuardPosY(null);
        setGuardPosX(null);
    }
    
    public void rotateGuard() {
        switch (getGuardDirection()) {
            case GuardDirection.UP:
                setMapField(getGuardPosX(), getGuardPosY(), ">");
                setGuardDirection(GuardDirection.RIGHT);
                break;
            case GuardDirection.RIGHT:
                setMapField(getGuardPosX(), getGuardPosY(), "∨");
                setGuardDirection(GuardDirection.DOWN);
                break;
            case GuardDirection.DOWN:
                setGuardDirection(GuardDirection.LEFT);
                setMapField(getGuardPosX(), getGuardPosY(), "<");
                break;
            case GuardDirection.LEFT:
                setGuardDirection(GuardDirection.UP);
                setMapField(getGuardPosX(), getGuardPosY(), "^");
                break;
        }
    }
    
    public int countUniqueMoves() {
        int count = 0;
        for(ArrayList<String> mapRow: getMapGrid()) {
            for(String field: mapRow) {
                if(field.equals("X")) count ++;
            }
        }
        return count;
    }
}
