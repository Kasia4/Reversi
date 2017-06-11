package controller;

import java.io.PrintWriter;

import model.Pawn;
import util.Vector2;

public class SendingPlayer implements AbstractPlayer {

	AbstractPlayer player;
	PrintWriter out;
	
	public SendingPlayer(AbstractPlayer player){
		this.player = player;
		this.out = player.getController().getPrintWriter();
	}
	@Override
	public void run() {
		player.run();
		out.println(Connection.posToString(player.getLastMovePos()));
		System.out.println();
	}

	@Override
	public void setController(GameController controller) {
		player.setController(controller);
		

	}

	@Override
	public Pawn getPawn() {
		return player.getPawn();
	}

	@Override
	public void setPawn(Pawn pawn) {
		player.setPawn(pawn);

	}
	@Override
	public GameController getController() {
		return player.getController();
	}
	@Override
	public Vector2 getLastMovePos() {
		return player.getLastMovePos();
	}

}
