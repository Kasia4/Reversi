
import model.Board;
import model.BoardSize;
public class Main {	
	
	public static void main(String[] args){
		Board board = new Board(BoardSize.SMALL);
		board.printOut();
	}
}
