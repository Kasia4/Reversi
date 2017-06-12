package controller;

import ai.AlphaBeta;

import java.util.Random;


import model.Game;
import ai.Heuristics;
import model.Pawn;
import util.Vector2;

public class AIPlayer extends Player {

	Game gameHandle;
	AlphaBeta inteligence;
	Heuristics heuristicFunction;
	
	Random r;
	public AIPlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);

		heuristicFunction = new Heuristics(gameHandle.getBoardSize());
		Game startGame = gameHandle; // add clone
		inteligence = new AlphaBeta(startGame, heuristicFunction);
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public void run() {
		
		inteligence.setCurrentGame(gameHandle);
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
