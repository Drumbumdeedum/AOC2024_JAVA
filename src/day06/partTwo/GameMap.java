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
    
    public void addMapRow(ArrayList<MapField> row) {
        this.mapFields.add(row);
    }
    
    public Guard getGuard() {
        return guard;
    }
    
    public void setGuard(Guard guard) {
        this.guard = guard;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(ArrayList<MapField> mapRow: mapFields) {
            for(MapField field: mapRow) {
                if(field.getPosX() == getGuard().getPosX() && field.getPosY() == getGuard().getPosY()) {
                    builder.append(getGuard());
                } else {
                    builder.append(field);
                }
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
