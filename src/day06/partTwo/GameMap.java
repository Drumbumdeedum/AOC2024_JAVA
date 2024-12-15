package day06.partTwo;

import java.util.ArrayList;

public class GameMap {
    private int width;
    private int height;
    private ArrayList<ArrayList<MapField>> mapFields = new ArrayList<>();
    private Guard guard;
    
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
    
    public ArrayList<ArrayList<MapField>> getMapFields() {
        return mapFields;
    }
    
    public void setMapFields(ArrayList<ArrayList<MapField>> mapFields) {
        this.mapFields = mapFields;
    }
    
    public void updateMapField(int posX, int posY, FieldType type, boolean visited) {
        MapField field = this.mapFields.get(posY).get(posX);
        field.setType(type);
        field.setVisited(visited);
        setMapField(posX, posY, field);
    }
    
    public void updateMapField(int posX, int posY, FieldType type, boolean visited, GuardDirection direction) {
        MapField field = this.mapFields.get(posY).get(posX);
        field.setType(type);
        field.setVisited(visited);
        field.setEnteredWithDirection(direction);
        setMapField(posX, posY, field);
    }
    
    public void setMapField(int posX, int posY, MapField field) {
        ArrayList<MapField> mapRow = getMapFields().get(posY);
        mapRow.set(posX, field);
        ArrayList<ArrayList<MapField>> updatedMap = getMapFields();
        updatedMap.set(posY, mapRow);
        setMapFields(updatedMap);
    }
    
    public void addMapRow(ArrayList<MapField> row) {
        this.mapFields.add(row);
    }
    
    public Guard getGuard() {
        return guard;
    }
    
    public void setGuard(Guard guard) {
        this.guard = guard;
    }
    
    public boolean hasNextField() {
        return switch (getGuard().getGuardDirection()) {
            case UP -> getGuard().getPosY() > 0;
            case DOWN -> getGuard().getPosY() < getHeight() - 1;
            case LEFT -> getGuard().getPosX() > 0;
            case RIGHT -> getGuard().getPosX() < getWidth() - 1;
        };
    }
    
    public MapField nextField() {
        return switch (getGuard().getGuardDirection()) {
            case UP -> getMapFields().get(getGuard().getPosY() - 1).get(getGuard().getPosX());
            case DOWN -> getMapFields().get(getGuard().getPosY() + 1).get(getGuard().getPosX());
            case LEFT -> getMapFields().get(getGuard().getPosY()).get(getGuard().getPosX() - 1);
            case RIGHT -> getMapFields().get(getGuard().getPosY()).get(getGuard().getPosX() + 1);
        };
    }
    
    public MapField currentField() {
        return getMapFields().get(getGuard().getPosY()).get(getGuard().getPosX());
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(ArrayList<MapField> mapRow: mapFields) {
            builder.append("\n");
            for(MapField field: mapRow) {
                if(field.getPosX() == getGuard().getPosX() && field.getPosY() == getGuard().getPosY()) {
                    builder.append(getGuard());
                } else {
                    builder.append(field);
                }
            }
        }
        return builder.toString();
    }
}
