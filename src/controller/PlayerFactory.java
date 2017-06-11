package controller;

import model.Pawn;

public class PlayerFactory {
	static AbstractPlayer producePlayer(PlayerType type, Pawn pawn, GameController controller, boolean sending)
	{
		AbstractPlayer newPlayer = null;
		switch(type){
		case AI:
			newPlayer = new AIPlayer(pawn, controller);
			break;
		case HUMAN:
			newPlayer = new HumanPlayer(pawn, controller);
			break;
		case REMOTE:
			newPlayer = new RemotePlayer(pawn, controller);
			break;
		case CONSOLE:
			newPlayer = new ConsolePlayer(pawn, controller);
			break;
		default:
			break;
		}
		if(newPlayer == null) return null;
		if(sending) newPlayer = new SendingPlayer(newPlayer);
		return newPlayer;

	}
}
