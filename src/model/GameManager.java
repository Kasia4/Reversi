package model;


public class GameManager {
	
	private Game game;
	
	private Player playerB;
	private Player playerW;
	
	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public Player getPlayerB() {
		return playerB;
	}
	
	public void setPlayerB(Player playerB) {
		this.playerB = playerB;
	}
	
	public Player getPlayerW() {
		return playerW;
	}
	
	public void setPlayerW(Player playerW) {
		this.playerW = playerW;
	}
	
	
	public void registerPlayers(Player playerB, Player playerW){
		this.playerB=playerB;
		this.playerW=playerW;
	}
}
