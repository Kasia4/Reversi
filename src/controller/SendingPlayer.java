package controller;

import java.io.PrintWriter;

import model.Pawn;

public class SendingPlayer implements AbstractPlayer {

	AbstractPlayer player;
	PrintWriter out;
	
	public SendingPlayer(AbstractPlayer player){
		this.player = player;
	}
	@Override
	public void run() {
		player.run();

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

}
