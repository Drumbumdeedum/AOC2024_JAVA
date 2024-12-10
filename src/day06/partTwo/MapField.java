package day06.partTwo;

import static day06.partTwo.FieldType.*;

public class MapField {
    private int posX;
    private int posY;
    private boolean visited;
    private FieldType type;
    
    public int getPosX() {
        return posX;
    }
    
    public void setPosX(int posX) {
        this.posX = posX;
    }
    
    public int getPosY() {
        return posY;
    }
    
    public void setPosY(int posY) {
        this.posY = posY;
    }
    
    public boolean isVisited() {
        return visited;
    }
    
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public FieldType getType() {
        return type;
    }
    
    public void setType(FieldType type) {
        this.type = type;
    }
    
    public void initTypeFromString(String type) {
        switch (type) {
            case "#" -> this.type = BLOCK;
            case "-" -> this.type = PATH_X;
            case "|" -> this.type = PATH_Y;
            case "+" -> this.type = CROSS;
            default -> this.type = EMPTY;
        }
    }
    
    @Override
    public String toString() {
        return switch(type) {
            case EMPTY -> ".";
            case BLOCK -> "#";
            case PATH_X -> "-";
            case PATH_Y -> "|";
            case CROSS -> "+";
        };
    }
}
