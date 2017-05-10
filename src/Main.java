
import java.util.ArrayList;

import model.Board;
import model.BoardSize;
import model.Field;
import model.Move;
import model.Pawn;
import util.Direction;
import util.Vector2;
public class Main {	
	
	public static void main(String[] args){
		Board board = new Board(BoardSize.SMALL);
		board.setField(new Vector2(5,4), Field.WHITE);
		board.printOut();
		
		System.out.println(board.canMove(new Move(new Vector2(4,2), Pawn.BLACK)));
		System.out.println(board.getFinishField(new Move(new Vector2(4,2), Pawn.BLACK), Direction.S));
		board.executeMove(new Move(new Vector2(4,2), Pawn.BLACK));
		board.printOut();
		System.out.println("\n");
		board.executeMove(new Move(new Vector2(5,2), Pawn.WHITE));
		board.printOut();
		System.out.println("\n");
		board.executeMove(new Move(new Vector2(3,2), Pawn.WHITE));
		board.printOut();
		System.out.println("\n");
		
		ArrayList<Vector2> fields = board.getAvailableFields(Pawn.BLACK);
		System.out.println(fields.size());
		for(Vector2 pos : fields){
			System.out.println(pos);
		}
		
	}
}
