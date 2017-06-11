package controller;

import model.Pawn;
import util.Vector2;

public abstract class Player implements AbstractPlayer {
	
	protected Pawn pawn;
	protected GameController controllerHandle;
	protected Vector2 lastMovePos;
	
	@Override
	public void setController(GameController controller) {
		controllerHandle = controller;
	}
	
	@Override
	public GameController getController(){
		return controllerHandle;
	}

	public Player(Pawn pawn, GameController controller){
		setPawn(pawn);
		setController(controller);
	}
	
	@Override
	public Pawn getPawn() {
		return pawn;
	}

	@Override
	public void setPawn(Pawn pawn) {
		this.pawn = pawn;
	}
	
	@Override
	public Vector2 getLastMovePos() {
		return lastMovePos;
	}
}
