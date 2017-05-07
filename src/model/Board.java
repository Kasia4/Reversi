package model;

import util.Matrix;
import util.Vector2;

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
		vector = Vector2.add(vector, Vector2.WN());
		Vector2 index = new Vector2(vector);
		setField(index, Field.BLACK);
		setField(Vector2.add(index, Vector2.E()), Field.WHITE);
		setField(Vector2.add(index, Vector2.ES()), Field.BLACK);
		setField(Vector2.add(index, Vector2.S()), Field.WHITE);
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
	
	
	public boolean canMove(Move move){
		if(!board.isValid(move.getPosition()))
			return false;
		if(!board.getField(move.getPosition()).isEmpty())
			return false;
		Vector2 directions[] = {Vector2.N(), Vector2.EN(), Vector2.E(), Vector2.ES(), Vector2.S(), Vector2.WS(), Vector2.W(), Vector2.WN()};
		for(int i = 0; i < 8; ++i){
			Vector2 curr = new Vector2(move.getPosition());
			boolean success = false;
			boolean hasOpp = false;
			while(true){
				curr = Vector2.add(curr, directions[i]);
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
	
	public Vector2 getFinishField(Move move, Vector2 dir){
		if(!board.isValid(move.getPosition()))
			return move.getPosition();
		if(!board.getField(move.getPosition()).isEmpty())
			return move.getPosition();
		
		Vector2 curr = new Vector2(move.getPosition());
		curr = Vector2.add(curr, dir);
		while(board.isValid(curr) && board.getField(curr) != Field.EMPTY){
			if(board.getField(curr) == move.getPawn().color()){
				return Vector2.sub(curr, dir);		
			}
			curr = Vector2.add(curr, dir);
		}
		return move.getPosition();
			
	}
	
	
	
}
