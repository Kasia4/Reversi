package model;

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
	
}
