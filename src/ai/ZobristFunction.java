package ai;

import java.security.*;

import model.Board;
import model.Field;
import model.Game;
import util.Vector2;

public class ZobristFunction {
	
	private long zobristArray[][][];
	private long turn;
	private Vector2 size;
	
	public ZobristFunction(Vector2 size) {
		this.size = size;
		zobristArray = new long[2][size.x][size.y]; // depends on pawn, and position of field
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
		turn = randomizeOne(); // used to distinguish states when board looks the same but different player has turn
		for(int i = 0; i < 2; ++i)
			for(int y = 0; y < size.y; ++y)
				for(int x = 0; x < size.x; ++x)
					zobristArray[i][y][x] = randomizeOne(); // first black second white
		
		
		
	}
	
	public long changeTurn( long key )
	{
		key ^= turn;
		return key;
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
	
	private long countGameKey(long key, int x, int y, Field color)
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
		if(playerChanged) // only for one field when the new move executed (new pawn placed)
			{
				key = changeTurn(key);
				key = countGameKey(key, x, y, color);
			}
		else
		{
			//  xor out from key
			if(color == Field.BLACK) // this means that before move this field was white
				key ^= zobristArray[1][y][x]; // undo recent xor for white field
			else if(color == Field.WHITE)
				key ^= zobristArray[0][y][x];
			
			key = countGameKey(key, x, y, color);
		}
		
		return key;
		
	}

}
