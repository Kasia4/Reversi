package model;

public class Move {

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
}
