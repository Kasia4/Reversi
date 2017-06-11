package controller;

import java.io.BufferedReader;
import java.io.IOException;

import model.Pawn;

public class RemotePlayer extends Player {

	BufferedReader in;
	
	public RemotePlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);
	}

	@Override
	public void run() {
		try {
			lastMovePos = Connection.stringToPos( in.readLine() );
		} catch (IOException e) {
			e.printStackTrace();
		}
		controllerHandle.sendMove(lastMovePos);
	}
	
	@Override
	public void setController(GameController controller) {
		super.setController(controller);
		in = controller.getBufferedReader();
	}

}
