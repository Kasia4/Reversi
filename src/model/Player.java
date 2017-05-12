package model;

import util.Vector2;

public class Player {
	
	private Pawn pawn;
	private Vector2 currentMove;
	
	public Vector2 getCurrentMove() {
		return currentMove;
	}

	public void setCurrentMove(Vector2 currentMove) {
		this.currentMove = currentMove;
	}

	public Player(Pawn pawn){
		this.pawn = pawn;
	}

	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}


}
