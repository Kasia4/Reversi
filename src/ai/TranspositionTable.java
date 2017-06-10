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
	
	public int getHash(long key)
	{
		return (int) key % size;
	}
	
	public State getState(Game game)
	{
		return transpositionTable.get(getHash(game.getZobristKey()));
	}
	public void registerState(Game game, State state)
	{
		transpositionTable.put(getHash(game.getZobristKey()), state);
	}

}
