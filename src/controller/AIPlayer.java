package controller;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import ai.AlphaBeta;
import ai.Heuristics;
import model.Game;
import model.Pawn;
import util.Vector2;

public class AIPlayer extends Player {

	Game gameHandle;
	AlphaBeta inteligence;
	Heuristics heuristicFunction;
	private static final int TIME_LIMIT = 800;
	
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
		
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.execute(chooseMove);
		try {
            if(!executor.awaitTermination(TIME_LIMIT, TimeUnit.MILLISECONDS)){
                executor.shutdownNow();
            }   
            

        } catch (InterruptedException e1) {
            executor.shutdownNow();
        }
		
        if(inteligence.getIterNextMove() == null)
            System.out.println("AI didn't choose the move");
        else
        {
            Vector2 currentMove = inteligence.getIterNextMove().getPosition();
            lastMovePos = currentMove; 
            controllerHandle.sendMove(currentMove);
            lastMovePos = currentMove;  
        }

	}
	
	@Override
	public void setController(GameController controller) {
		super.setController(controller);
		gameHandle = controller.getGame();
	}
}
