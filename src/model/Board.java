package model;

public class Board {
	private Matrix<Field> board;
	
	private int boardSize = 8;
	
	public Board(BoardSize size){
		this.boardSize = size.getValue();
		board = new Matrix<Field>(boardSize, boardSize);
		board.fill(Field.EMPTY);
		int index = boardSize/2 - 1;
		setField(index, index, Field.BLACK);
		setField(index, index + 1, Field.WHITE);
		setField(index + 1, index + 1, Field.BLACK);
		setField(index + 1, index, Field.WHITE);
	}
	
	public void setField(int x,int y, Field field){
		board.setField(x, y, field);
	}
	
	public Field getField(int x, int y){
		return board.getField(x, y);
	}
	
	
}
