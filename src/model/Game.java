package model;

import ai.ZobristFunction;
import util.Vector2;

public class Game {

	private Board board;
	private GameState gameState = GameState.TURN_B;
	private boolean emptyMovesEnabled = false;
	private boolean emptyMoveRequired = false;
	
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
		if(emptyMovesEnabled){
			if(emptyMoveRequired)
			{
				if(position.equals( Move.emptyMoveVector()))
				{
					board.executeMove(Move.emptyMove(gameState.getPawn()));
					if(gameState == GameState.TURN_B)
						gameState = GameState.TURN_W;
					else gameState = GameState.TURN_B;
					
					emptyMoveRequired = false;
					return true;
				}
				return false;
			}
			if(!position.equals( Move.emptyMoveVector()))
			{
				if(board.executeMove(new Move(position, gameState.getPawn())))
				{
					boolean whiteMovePossible = board.ifMovePossible(Pawn.WHITE);
					boolean blackMovePossible = board.ifMovePossible(Pawn.BLACK);
					if(gameState == GameState.TURN_B) {
						gameState = GameState.TURN_W;
						if(!whiteMovePossible)
						{
							emptyMoveRequired = true;
						}
					}
					else if(gameState == GameState.TURN_W) {
						gameState = GameState.TURN_B;
						if(!blackMovePossible)
						{
							emptyMoveRequired = true;
						}
					}
					if(!whiteMovePossible && !blackMovePossible)
						gameState = checkWinner();
					return true;
				}
				return false;
			}
		}
		else if(board.executeMove(new Move(position, gameState.getPawn()))){
	       
			boolean whiteMovePossible = board.ifMovePossible(Pawn.WHITE);
			boolean blackMovePossible = board.ifMovePossible(Pawn.BLACK);
			
			if(gameState == GameState.TURN_B && whiteMovePossible)
	            gameState = GameState.TURN_W;
	        else if(gameState == GameState.TURN_W && blackMovePossible)
	            gameState = GameState.TURN_B;
	       
	        if(!whiteMovePossible && !blackMovePossible){
	            gameState = checkWinner();
	        }
	        return true;
	    }
	    return false;
	}
	public Move undoMove(){
		PastMove move = board.undoMove();
		if(move == null) return null;
		if(move.getPawn() == Pawn.BLACK)
			gameState = GameState.TURN_B;
		else 
			gameState = GameState.TURN_W;
		return move;
	}
	public GameState checkWinner()
	{
		if(board.getFieldsNumber(Field.BLACK) > board.getFieldsNumber(Field.WHITE))
            return GameState.WIN_B;
        else if(board.getFieldsNumber(Field.BLACK) < board.getFieldsNumber(Field.WHITE))
            return GameState.WIN_W;
        else
            return GameState.DRAW;
	}
}
