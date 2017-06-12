package ai;

import java.util.*;

import model.Game;

public class TranspositionTable{
	
	private int size;
	HashMap<Integer, State> transpositionTable;
	
	TranspositionTable(int size)
	{
		this.size = size;
		transpositionTable = new HashMap<Integer, State> (size);
		
	}
	
	public int getGameHash(Game game)
	{
		long hash = game.getZobristKey() % size;
		return (int)hash;
	}
	
	public State getState(Game game)
	{
		return transpositionTable.get(getGameHash(game));
	}
	public void registerState(Game game, State state)
	{
		transpositionTable.put(getGameHash(game), state);
	}

}
