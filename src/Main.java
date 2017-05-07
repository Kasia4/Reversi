
import model.Board;
import model.BoardSize;
import model.Field;
import model.Move;
import model.Pawn;
import util.Vector2;
public class Main {	
	
	public static void main(String[] args){
		Board board = new Board(BoardSize.SMALL);
		board.setField(new Vector2(5,4), Field.WHITE);
		board.printOut();
		System.out.println(board.canMove(new Move(new Vector2(4,2), Pawn.BLACK)));
		System.out.println(board.getFinishField(new Move(new Vector2(4,2), Pawn.BLACK), Vector2.S()));
		
	}
}
