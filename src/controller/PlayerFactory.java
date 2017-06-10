package controller;

import model.Pawn;

public class PlayerFactory {
	static Player producePlayer(PlayerType type, Pawn pawn, GameController controller)
	{
		switch(type){
		case AI:
			break;
		case HUMAN:
			return new HumanPlayer(pawn, controller);
		case REMOTE_AI:
			break;
		default:
			break;
		
		}
		return null;
	}
}
