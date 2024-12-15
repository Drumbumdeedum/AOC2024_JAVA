package day06.partTwo;

import static day06.partTwo.GuardDirection.*;

public class Guard {
    private int posX;
    private int posY;
    private GuardDirection guardDirection = UP;
    
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
    
    public GuardDirection getGuardDirection() {
        return guardDirection;
    }
    
    public void setGuardDirection(GuardDirection guardDirection) {
        this.guardDirection = guardDirection;
    }
    
    public void moveGuard(int mapWidth, int mapHeight) {
        switch (getGuardDirection()) {
            case UP: {
                moveGuardUp();
                break;
            }
            case DOWN: {
                moveGuardDown(mapHeight);
                break;
            }
            case RIGHT: {
                moveGuardRight(mapWidth);
                break;
            }
            case LEFT: {
                moveGuardLeft();
                break;
            }
        }
    }
    
    public void moveGuardUp() {
        setPosY(getPosY() - 1);
        if(getPosY() < 0) {
            //System.out.println("GUARD HAS LEFT THE MAP!");
        }
    }
    
    public void moveGuardDown(int mapHeight) {
        setPosY(getPosY() + 1);
        if(getPosY() >= mapHeight) {
            //System.out.println("GUARD HAS LEFT THE MAP!");
        }
        
    }
    
    public void moveGuardRight(int mapWidth) {
        setPosX(getPosX() + 1);
        if(getPosX() >= mapWidth) {
            //System.out.println("GUARD HAS LEFT THE MAP!");
        }
    }
    
    public void moveGuardLeft() {
        setPosX(getPosX() - 1);
        if(getPosX() < 0) {
            //System.out.println("GUARD HAS LEFT THE MAP!");
        }
    }
    
    public void turnGuardRight() {
        switch (getGuardDirection()) {
            case UP -> setGuardDirection(RIGHT);
            case DOWN -> setGuardDirection(LEFT);
            case LEFT -> setGuardDirection(UP);
            case RIGHT -> setGuardDirection(DOWN);
        }
    }
    
    public boolean guardOnMap(int mapWidth, int mapHeight) {
        return getPosX() > -1 && getPosY() > -1 &&
                getPosX() < mapWidth && getPosY() < mapHeight;
    }
    
    @Override
    public String toString() {
        return switch (guardDirection) {
            case UP -> "^";
            case DOWN -> "∨";
            case LEFT -> "<";
            case RIGHT -> ">";
        };
    }
}
