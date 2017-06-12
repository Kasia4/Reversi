package model;

import java.util.ArrayList;

import util.Vector2;

public class MoveResult {
	
	private Field field;
	private ArrayList<Vector2> positions;
	
	public MoveResult(){
		positions = new ArrayList<Vector2>();
	}
	
	public void clear(){
		positions.clear();
	}
	
	public void addPosition(Vector2 position){
		positions.add(position);
	}
	
	public void setField(Field field){
		this.field = field;
	}
	
	public Field getField(){
		return field;
	}
	
	public ArrayList<Vector2> getPositions(){
		return positions;
	}
	
	public String toString(){
		return "" + field.toString()+ "\tpola\t" + positions;
	}
}
