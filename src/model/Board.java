package model;

import util.Matrix;
import util.Vector2;
import util.Direction;

public class Board {
	private Matrix<Field> board;
	
	private Vector2 boardSize;
	
	public Board(BoardSize size){
		this.boardSize = size.getSize();
		init();
	}
	
	private void init(){
		board = new Matrix<Field>(boardSize);
		board.fill(Field.EMPTY);
		Vector2 vector = Vector2.div(boardSize, 2);
		System.out.println(vector);
		vector = Vector2.add(vector, Direction.NW.v);
		Vector2 index = new Vector2(vector);
		setField(index, Field.BLACK);
		System.out.println(vector);
		setField(Vector2.add(index, Direction.E.v), Field.WHITE);
		setField(Vector2.add(index, Direction.SE.v), Field.BLACK);
		setField(Vector2.add(index, Direction.S.v), Field.WHITE);
	}
	
	public void setField(Vector2 pos, Field field){
		board.setField(pos, field);
	}
	
	public Field getField(Vector2 pos){
		return board.getField(pos);
	}
	
	public void printOut(){
		board.printOut();
	}
	
	/** Executes given move on board. Places pawn at given position and reverses adequate pawns on board.
	 * @return true when move was possible, false otherwise.
	 */
	public boolean executeMove(Move move){
		if(!canMove(move))
			return false;
		Vector2 pawnPos = move.getPosition();
		Field color = move.getPawn().color();
		for (Direction dir : Direction.values()) {
			Vector2 finishPos = getFinishField(move, dir);
			if(finishPos.equals(pawnPos))
				continue;
			for(Vector2 currPos = Vector2.add(pawnPos, dir.v); !currPos.equals(finishPos); currPos = Vector2.add(currPos, dir.v))
				board.setField(currPos, color);
			
		}
		setField(pawnPos, color);
		return true;
	}
	public boolean canMove(Move move){
		if(!board.isValid(move.getPosition()))
			return false;
		if(!board.getField(move.getPosition()).isEmpty())
			return false;
		for(Direction dir : Direction.values())
		{
			Vector2 curr = new Vector2(move.getPosition());
			boolean success = false;
			boolean hasOpp = false;
			while(true){
				curr = Vector2.add(curr, dir.v);
				if(!board.isValid(curr)) break;
				if(board.getField(curr) == Field.EMPTY) break;
				if(board.getField(curr) == move.getPawn().opposite()){
					hasOpp = true;
					continue;
				}
				if(board.getField(curr) == move.getPawn().color()){
					success = true;
					break;
				}			
			}
			if(success && hasOpp) return true;
		}
		return false;
	}
	
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
	
	
	
}
