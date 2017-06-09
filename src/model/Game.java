package model;

import util.Vector2;

public class Game {

	private Board board;
	private GameState gameState = GameState.TURN_B;
	
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
	public boolean makeMove(Vector2 position){
	    if(board.executeMove(new Move(position, gameState.getPawn()))){
	        if(gameState == GameState.TURN_B && board.ifMovePossible(Pawn.WHITE))
	            gameState = GameState.TURN_W;
	        else if(gameState == GameState.TURN_W && board.ifMovePossible(Pawn.BLACK))
	            gameState = GameState.TURN_B;
	        
	        if(!board.ifMovePossible(Pawn.BLACK) && !board.ifMovePossible(Pawn.WHITE)){
	            if(board.getFieldsNumber(Field.BLACK) > board.getFieldsNumber(Field.WHITE))
	                gameState = GameState.WIN_B;
	            else if(board.getFieldsNumber(Field.BLACK) < board.getFieldsNumber(Field.WHITE))
	                gameState = GameState.WIN_W;
	            else
	                gameState = GameState.DRAW;
	        }
	        return true;
	    }
	    return false;
	}
}
