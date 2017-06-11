package controller;

import model.Pawn;

public class PlayerFactory {
	static AbstractPlayer producePlayer(PlayerType type, Pawn pawn, GameController controller, boolean sending)
	{
		AbstractPlayer newPlayer = null;
		switch(type){
		case AI:
			newPlayer = new AIPlayer(pawn, controller);
		case HUMAN:
			newPlayer = new HumanPlayer(pawn, controller);
		case REMOTE:
			newPlayer = new RemotePlayer(pawn, controller);
		case CONSOLE:
			newPlayer = new ConsolePlayer(pawn, controller);
		default:
			break;
		}
		if(newPlayer == null) return null;
		if(sending) newPlayer = new SendingPlayer(newPlayer);
		return newPlayer;

	}
}
