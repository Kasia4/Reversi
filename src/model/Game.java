package model;

import java.util.ArrayList;

import ai.ZobristFunction;
import util.Vector2;

public class Game {

	private Board board;
	private BoardSize boardSize;
	private GameState gameState = GameState.TURN_B;


	private boolean emptyMovesEnabled = true;
	private boolean emptyMoveRequired = false;
	
	private ZobristFunction zobrist;
	private long zobristKey;
	
//	private void changeGameKey(boolean firstMove, Move move)
//	{
//		if(firstMove)
//		{
//			//zobrist.changeTurn(zobristKey);
//			zobrist.updateGameKey(zobristKey, move.getPosition(), move.getPawn().color() , true);
//		}
//			
//		else
//			zobrist.updateGameKey(zobristKey, move.getPosition(), move.getPawn().color(), false);
//			
//			
//	}
	
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
	
	public Game(BoardSize boardSize){
	    board = new Board(boardSize);
	    this.boardSize = boardSize;
	}
	
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public BoardSize getBoardSize(){
		return boardSize;
	}
	public GameState getGameState() {
		return gameState;
	}
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public Pawn currentPawn(){
		return gameState.getPawn();
	}
	
	public ArrayList<Vector2> getMoves()
	{
		return board.getAvailableFields(currentPawn());
	}
	public boolean getEmptyMoveRequired() {
		return emptyMoveRequired;
	}
	public boolean makeMove(Vector2 position){
			if(!position.equals( Move.emptyMoveVector()))
			{
				Move next = new Move(position, gameState.getPawn());
				if(board.executeMove(next))
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
				
				MoveResult changedFields = board.getLastMoveResult();
				Field color = changedFields.getField();
				zobrist.updateGameKey(zobristKey, next.getPosition(), color, true);
				ArrayList<Vector2> positions = changedFields.getPositions();
				for( Vector2 pos : positions)
					zobrist.updateGameKey(zobristKey, position, color, false);
					return true;
				}
				return false;
			}
			else 
			{
				if(emptyMoveRequired)
				{
					board.executeMove(Move.emptyMove(gameState.getPawn()));
					if(gameState == GameState.TURN_B)
						gameState = GameState.TURN_W;
					else gameState = GameState.TURN_B;
					zobrist.changeTurn(zobristKey);
					emptyMoveRequired = false;
					return true;
				}
				return false;
			}
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
