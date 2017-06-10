package controller;

import model.Pawn;
import util.Vector2;

public abstract class Player implements Runnable {
	
	private Pawn pawn;
	private Vector2 currentMove;
	private GameController controllerHandle;
	
	public void setController(GameController controller)
	{
		controllerHandle = controller;
	}
	public Vector2 getCurrentMove() {
		return currentMove;
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
