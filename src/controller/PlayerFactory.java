package controller;

import model.Pawn;

public class PlayerFactory {
	static Player producePlayer(PlayerType type, Pawn pawn, GameController controller)
	{
		switch(type){
		case AI:
			return new AIPlayer(pawn, controller);
		case HUMAN:
			return new HumanPlayer(pawn, controller);
		case REMOTE_AI:
			break;
		case CONSOLE:
			return new ConsolePlayer(pawn, controller);
		default:
			break;
		
		}
		return null;
	}
}
