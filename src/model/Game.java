package model;

import ai.ZobristFunction;
import util.Vector2;

public class Game {

	private Board board;
	private GameState gameState = GameState.TURN_B;
	private ZobristFunction zobrist;
	public ZobristFunction getZobrist() {
		return zobrist;
	}
	public void setZobrist(ZobristFunction zobrist) {
		this.zobrist = zobrist;
	}
	public long getZobristKey() {
		return zobristKey;
	}
	public void setZobristKey(long zobristKey) {
		this.zobristKey = zobristKey;
	}
	private long zobristKey;
	
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
	public void undoMove(){
		PastMove move = board.undoMove();
		if(move == null) return;
		if(move.getPawn() == Pawn.BLACK)
			gameState = GameState.TURN_B;
		else 
			gameState = GameState.TURN_W;
	}
}
