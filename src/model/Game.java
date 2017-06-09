package model;

public class Game {

	private Board board;
	private GameState gameState;
	
	public Game(BoardSize boardSize){
	    board = new Board(boardSize);
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	
	

}
