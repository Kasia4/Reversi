package controller;

import model.Pawn;
import util.Vector2;

public abstract class Player implements Runnable {
	
	protected Pawn pawn;
	protected GameController controllerHandle;
	
	public void setController(GameController controller) {
		controllerHandle = controller;
	}

	public Player(Pawn pawn, GameController controller){
		setPawn(pawn);
		setController(controller);
	}

	public Pawn getPawn() {
		return pawn;
	}

	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
}
