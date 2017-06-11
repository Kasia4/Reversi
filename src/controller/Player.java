package controller;

import model.Pawn;

public abstract class Player implements AbstractPlayer {
	
	protected Pawn pawn;
	protected GameController controllerHandle;
	
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
}
