package controller;

import model.Game;
import model.Pawn;
import util.Vector2;

public class AIPlayer extends Player {

	Game gameHandle;
	
	public AIPlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		Vector2 currentMove = gameHandle.getBoard().getAvailableFields(pawn).get(0);
		controllerHandle.sendMove(currentMove);
		lastMovePos = currentMove;		
	}
	
	@Override
	public void setController(GameController controller) {
		super.setController(controller);
		gameHandle = controller.getGame();
	}
	

}
