package model;

import util.Matrix;
import util.Vector2;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import util.Direction;

public class Board implements Cloneable {
	private Matrix<Field> board;
	private Vector2 boardSize;
	private MoveResult lastMoveResult = new MoveResult();
	private Stack<PastMove> doneMoves = new Stack<PastMove>();
	
	public static int i = 0;
	public Board()
	{
		
	}
    public Board(BoardSize size){
		this.boardSize = size.getSize();
		init();
	}
	
	/**
	 * Initializes board with set size. Place pawns at start positions.
	 */
	private void init(){
		board = new Matrix<Field>(boardSize);
		board.fill(Field.EMPTY);
		Vector2 vector = Vector2.div(boardSize, 2);
		vector = Vector2.add(vector, Direction.NW.v);
		Vector2 index = new Vector2(vector);
		setField(index, Field.BLACK);
		setField(Vector2.add(index, Direction.E.v), Field.WHITE);
		setField(Vector2.add(index, Direction.SE.v), Field.BLACK);
		setField(Vector2.add(index, Direction.S.v), Field.WHITE);
	}
	
	@Override
	public Board clone()
	{
		Board toReturn = new Board();
		toReturn.boardSize = this.getBoardSize();
		toReturn.board = this.board.clone();
		return toReturn;
	}
	
	/**
	 * Sets field of specified type on given position
	 * @param pos Field position
	 * @param field Field type
	 */
	public void setField(Vector2 pos, Field field){
		board.setField(pos, field);
	}
	
	/**
	 * Returns type of field at given position
	 * @param pos field position
	 * @return field type
	 */
	public Field getField(Vector2 pos){
		return board.getField(pos);
	}
	
	public void printOut(){
		board.printOut();
	}
	
	/**
	 * Executes given move on board. Places pawn at given position and reverses adequate pawns on board.
	 * @param move Specifies player and placed pawn position 
	 * @return true when move was possible, false otherwise.
	 */
	public boolean executeMove(Move move){
		if(move.isEmpty())
		{
			//System.out.println("pusty");
			doneMoves.push(new PastMove(move));
			return true;
		}
		if(!canMove(move))
		{
			return false;
		}
		lastMoveResult.clear();
		lastMoveResult.setField(move.getPawn().color);
		PastMove pastMove = new PastMove(move);
		int currentAnchor;
		Vector2 pawnPos = move.getPosition();
		Field color = move.getPawn().color();
		
		for (Direction dir : Direction.values()) {
			currentAnchor = 0;
			Vector2 finishPos = getFinishField(move, dir);
			if(finishPos.equals(pawnPos))
				continue;
			for(Vector2 currPos = Vector2.add(pawnPos, dir.v); !currPos.equals(finishPos); currPos = Vector2.add(currPos, dir.v)) {
				setField(currPos, color);
				lastMoveResult.addPosition(currPos);
				++currentAnchor;
			}
			pastMove.setAnchor(dir, currentAnchor);
		}
		setField(pawnPos, color);
		doneMoves.push(pastMove);
		//System.out.println("do: ");
		  // System.out.println(lastMoveResult);
		return true;
	}
	
	/**
	 * Checks correctness of the move
	 * @param move Tested move
	 * @return true if given move is correct, false otherwise
	 */
	public boolean canMove(Move move){
		return canMove(move.getPawn(), move.getPosition());
	}
	
	/**
	 * Checks if 
	 * @param field
	 * @param pos
	 * @return
	 */
	public boolean canMove(Pawn pawn, Vector2 pos){
		if(!board.isValid(pos))
			return false;
		if(!board.getField(pos).isEmpty())
			return false;
		for(Direction dir : Direction.values())
		{
			Vector2 curr = new Vector2(pos);
			boolean success = false;
			boolean hasOpp = false;
			while(true){
				curr = Vector2.add(curr, dir.v);
				if(!board.isValid(curr)) break;
				if(board.getField(curr) == Field.EMPTY) break;
				if(board.getField(curr) == pawn.opposite()){
					hasOpp = true;
					continue;
				}
				if(board.getField(curr) == pawn.color()){
					success = true;
					break;
				}			
			}
			if(success && hasOpp) return true;
		}
		
		return false;
	}
	/**
	 * Finds the nearest specified in move player's pawn in given direction 
	 * @param move Specifies move
	 * @param dir Direction of search
	 * @return Found pawn position if it exists, started position otherwise
	 */
	public Vector2 getFinishField(Move move, Direction dir){
		if(!board.isValid(move.getPosition()))
			return move.getPosition();
		if(!board.getField(move.getPosition()).isEmpty())
			return move.getPosition();
		
		Vector2 curr = new Vector2(move.getPosition());
		curr = Vector2.add(curr, dir.v);
		while(board.isValid(curr) && board.getField(curr) != Field.EMPTY){
			if(board.getField(curr) == move.getPawn().color()){
				return curr;
			}
			curr = Vector2.add(curr, dir.v);
		}
		return move.getPosition();
			
	}
	
	/**
	 * Looks for fields positions available for the given pawn.
	 * @param pawn Pawn
	 * @return Available positions list
	 */
	public ArrayList<Vector2> getAvailableFields(Pawn pawn){
		ArrayList<Vector2> available = new ArrayList<Vector2>();
		Vector2 pos = new Vector2();
		for(pos.y = 0; pos.y < boardSize.y; ++pos.y){
			for(pos.x = 0; pos.x < boardSize.x; ++pos.x){
				if(canMove(pawn, pos)){
					available.add(new Vector2(pos));
				}
			}
		}
//		if(available.isEmpty())
//			available.add(Move.emptyMoveVector());
		return available;
	}
	
	/**
	 * Counts available moves for a given pawn
	 * @param pawn Pawn
	 * @return The number of available moves
	 */
	public int availableFieldsNumber(Pawn pawn){
		int counter = 0;
		Vector2 pos = new Vector2();
		for(pos.y = 0; pos.y < boardSize.y; ++pos.y){
			for(pos.x = 0; pos.x < boardSize.x; ++pos.x){
				if(canMove(pawn, pos)){
					++counter;
				}
			}
		}
		return counter;
	}
	/**
	 * Checks whether the move is possible for a given pawn
	 * @param pawn Pawn
	 * @return true when move is possible, false otherwise
	 */
	public boolean ifMovePossible(Pawn pawn){
		Vector2 pos = new Vector2();
		for(pos.y = 0; pos.y < boardSize.y; ++pos.y){
			for(pos.x = 0; pos.x < boardSize.x; ++pos.x){
				if(canMove(pawn, pos)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Finds positions of fields with given type
	 * @param field Field type to comparison
	 * @return ArrayList of fields positions
	 */
	public ArrayList<Vector2> getFields(Field field){
		ArrayList<Vector2> positions = new ArrayList<Vector2>();
		Vector2 pos = new Vector2();
		for(pos.y = 0; pos.y < boardSize.y; ++pos.y){
			for(pos.x = 0; pos.x < boardSize.x; ++pos.x){
				if(board.getField(pos) == field){
					positions.add(new Vector2(pos));
				}
			}
		}
		return positions;
	}
	
	/**
	 * Counts the number of fields with given type
	 * @param field Field type to comparison
	 * @return Number of fields with given type
	 */
	public int getFieldsNumber(Field field){
		int count = 0;
		Vector2 pos = new Vector2();
		for(pos.y = 0; pos.y < boardSize.y; ++pos.y){
			for(pos.x = 0; pos.x < boardSize.x; ++pos.x){
				if(board.getField(pos) == field){
					++count;
				}
			}
		}
		return count;
	}
	
	public Vector2 getBoardSize() {
        return boardSize;
	}

	public void setBoardSize(Vector2 boardSize) {
        this.boardSize = boardSize;
	}
    
	public MoveResult getLastMoveResult(){
		return lastMoveResult;
	}
   
	public PastMove undoMove(){
		if(doneMoves.isEmpty())return null;
		PastMove lastMove = doneMoves.pop();
		lastMoveResult.clear();
		lastMoveResult.setField(lastMove.getPawn().opposite);
		Field field = lastMove.getPawn().opposite();
		for(Direction dir : Direction.values())
		{
			Vector2 curr = new Vector2(lastMove.getPosition());
			int replaced = 0;
			while(replaced < lastMove.getAnchor(dir)){
				curr = Vector2.add(curr, dir.v);
				setField(curr, field);
				lastMoveResult.addPosition(curr);
				++replaced;
			}
		}
		setField(lastMove.getPosition(), Field.EMPTY);
		
		//System.out.println("undo: ");
		//System.out.println(lastMoveResult);
		return lastMove;
	}
}
