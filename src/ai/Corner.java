package ai;

import model.Field;
import util.Vector2;

public class Corner{

    private Field field;
    private Vector2 position;
    private Vector2 horizonal;
    private Vector2 vertical;
    
    public Corner(Field field, Vector2 position) {
        this.field = field;
        this.position = position;
        if(position.x == 0)
            horizonal = Vector2.E();
        else
            horizonal = Vector2.W();
        if(position.y == 0)
            vertical = Vector2.S();
        else
            vertical = Vector2.N();
    }
    public Vector2 getHorizonal(){
        return horizonal;
    }
    public Vector2 getVertical(){
        return vertical;
    }
    public Field getField(){
        return field;
    }
    public Vector2 getPosition(){
        return position;
    }
}
