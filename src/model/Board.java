package model;

public class Board {
	private Matrix<Field> board;
	
	private int boardSize = 8;
	
	public Board(){
		board = new Matrix<Field>(boardSize);
	}
}
