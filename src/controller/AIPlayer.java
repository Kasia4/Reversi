package controller;

import model.Game;
import model.Pawn;

public class AIPlayer extends Player {

	Game gameHandle;
	
	public AIPlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void run() {
		controllerHandle.sendMove(gameHandle.getBoard().getAvailableFields(pawn).get(0));
		
	}
	
	@Override
	public void setController(GameController controller) {
		super.setController(controller);
		gameHandle = controller.getGame();
	}
	

}
