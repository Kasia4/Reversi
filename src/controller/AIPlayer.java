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
	private static final int TIME_LIMIT = 1000;
	
	Random r;
	public AIPlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);

		heuristicFunction = new Heuristics(gameHandle.getBoardSize());
		heuristicFunction.setPlayerPawn(pawn);
		Game startGame = gameHandle.clone(); // add clone
		inteligence = new AlphaBeta(startGame, heuristicFunction);
		// TODO Auto-generated constructor stub

	}
	
	@Override
	public void run() {
		
		inteligence.setCurrentGame(gameHandle);
		Thread chooseMove = new Thread(inteligence);
		chooseMove.start();
		try {
			chooseMove.join(TIME_LIMIT);
			if(inteligence.getIterNextMove() == null)
				System.out.println("AI didn't choose the move");
			else
			{
				Vector2 currentMove = inteligence.getIterNextMove().getPosition();
				controllerHandle.sendMove(currentMove);
				lastMovePos = currentMove;	
			}
			//Vector2 currentMove = gameHandle.getBoard().getAvailableFields(pawn).get(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
	
	@Override
	public void setController(GameController controller) {
		super.setController(controller);
		gameHandle = controller.getGame();
	}
}
