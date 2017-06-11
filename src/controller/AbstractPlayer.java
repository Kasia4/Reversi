package controller;

import model.Pawn;
import util.Vector2;

public interface AbstractPlayer extends Runnable {
	public void setController(GameController controller);
	public GameController getController();
	public Pawn getPawn();
	public void setPawn(Pawn pawn);
	public Vector2 getLastMovePos();
}
