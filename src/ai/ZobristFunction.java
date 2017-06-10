package ai;

import java.security.*;

import model.Board;
import model.Field;
import model.Game;
import util.Vector2;

public class ZobristFunction {
	
	private long zobristArray[][][];
	private long blackTurn;
	private Vector2 size;
	
	public ZobristFunction(Vector2 size) {
		this.size = size;
		
		randomizeValues();
	}
	
	private long randomizeOne()
	{
		SecureRandom randomizer = new SecureRandom();
		return randomizer.nextLong();
		
	}
	// value in zobristArray depends on color of pawn and position
	private void randomizeValues()
	{
		zobristArray = new long[2][size.x][size.y];
		for(int i = 0; i < 2; ++i)
			for(int y = 0; y < size.y; ++y)
				for(int x = 0; x < size.x; ++x)
					zobristArray[i][y][x] = randomizeOne();
		
		blackTurn = randomizeOne(); // used to distinguish states when board looks the same but different player has turn
		
	}
	
	public long getGameKey(Game game) {
		long key = 0;
		Board board = game.getBoard();
		for(int y = 0; y < size.y; ++y)
			for(int x = 0; x < size.x; ++x)
			{
				Field currentField = board.getField(new Vector2(x,y));
				if(currentField == Field.BLACK)
					key ^= zobristArray[0][y][x];
				else if( currentField == Field.WHITE)
					key ^= zobristArray[1][y][x];
				
			}
		key = changeTurn(key);
		return key;
		
	}
	public long changeTurn( long key )
	{
		key ^= blackTurn;
		return key;
	}
	
	public long countGameKey(long key, int x, int y, Field color)
	{
		if(color == Field.BLACK)
			key ^= zobristArray[0][y][x];
		else if(color == Field.WHITE)
			key ^= zobristArray[1][y][x];
		return key;
	}
	
	public long updateGameKey(long oldKey, Vector2 changedField, Field color, boolean playerChanged)
	{
		long key = oldKey;
		int x = changedField.x;
		int y = changedField.y;
		if(playerChanged) // only for one field when the new move executed
			{
				key = changeTurn(key);
				key = countGameKey(key, x, y, color);
			}
		else
		{
			if(color == Field.BLACK) // this means that before move this field was white
				key ^= zobristArray[1][y][x]; // undo recent xor for white field
			else if(color == Field.WHITE)
				key ^= zobristArray[0][y][x];
			
			key = countGameKey(key, x, y, color);
		}
		
		return key;
		
	}

}
