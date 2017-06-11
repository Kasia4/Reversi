package controller;

import model.Pawn;

public interface AbstractPlayer extends Runnable {
	public void setController(GameController controller);
	public GameController getController();
	public Pawn getPawn();
	public void setPawn(Pawn pawn);
}
