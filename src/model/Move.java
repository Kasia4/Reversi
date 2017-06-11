package model;

import util.Vector2;

public class Move {

	private static final Vector2 EMPTY_REP = new Vector2(-1,-1);
	
	private Vector2 position;
	private Pawn pawn;
	
	public Move(Vector2 position, Pawn pawn){
		this.position = position;
		this.pawn = pawn;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
	
	public boolean isEmpty() {
		return position.equals( EMPTY_REP );
	}
	
	static public Move emptyMove(Pawn pawn){
		return new Move( new Vector2( EMPTY_REP ), pawn );
	}
}
