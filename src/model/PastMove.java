package model;

import util.Direction;
import util.Vector2;

public class PastMove extends Move {

	private int[] anchors = new int[8];
	
	public PastMove(Move move){
		super(move.getPosition(), move.getPawn());
	}
	public PastMove(Vector2 position, Pawn pawn) {
		super(position, pawn);
	}
	
	public void setAnchor(Direction dir, int value) {
		anchors[dir.id()] = value;
	}
	
	public int getAnchor(Direction dir) {
		return anchors[dir.id()];
	}
	
	public String toString(){
		String s = new String();
		for(Direction dir : Direction.values())
		{
			s += dir.toString() + ": " + anchors[dir.id()] + "\t";
		}
		return s;
	}
}
