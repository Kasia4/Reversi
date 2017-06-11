package ai;

import java.util.ArrayList;

import model.Game;
import model.Heuristics;
import model.Move;
import util.Vector2;

public class AlphaBeta {
	private Game game;
	private TranspositionTable transpositionTable;
	private Heuristics heuristicFunction;
	private Move bestMove;
	private int depth;
	
	
	AlphaBeta(Game game, int depth, Heuristics heuristicFunction)
	{
		this.game = game;
		this.depth = depth;
		this.heuristicFunction = heuristicFunction;
		int size = game.getBoard().getBoardSize().x;
		transpositionTable = new TranspositionTable(size);
		
	}
	
	
	public int alphaBeta (Game game, int depth, int alpha, int beta, MinMaxNode node)
	{
		int nestedAlpha = alpha;
		int nestedBeta = beta;
		int value = 0;
		if(depth == 0 || game.getGameState().isTerminal())
			return (int)heuristicFunction.heuristicTest(game.getBoard());
		
		State currentState = transpositionTable.getState(game);
		if(currentState != null && currentState.getDepth() >= depth) // value is good enough
		{
			ValueFlag flag = currentState.getFlag();
			value = currentState.getValue();
			if(flag == ValueFlag.UPPER)
				nestedBeta = Math.max(nestedBeta, value);
			else if(flag ==  ValueFlag.LOWER)
				nestedAlpha = Math.max(nestedAlpha, value);
			else
				nestedAlpha = nestedBeta = value;
			if(flag == ValueFlag.ACCURATE || nestedAlpha >= nestedBeta)
				return value;
			
		}
		
		// ADD USE OF BESTMOVE FROM STATE FROM TRANSPOSITION TABLE !!!
		int bestValue;
		ArrayList<Vector2> nextMoves = game.getMoves();
		if( node == MinMaxNode.MAX)
		{
			for(Vector2 move : nextMoves){
				game.makeMove(move);
				value = alphaBeta(game,depth - 1, nestedAlpha, nestedBeta, MinMaxNode.MIN);
				
				nestedAlpha = Math.max(nestedAlpha, value);
				if(nestedAlpha >= nestedBeta)
					break; // beta cut off
			}
			bestValue = nestedAlpha; // return alpha
	
		}
		else // MIN node
		{
			for(Vector2 move : nextMoves){
				
			}
		}
				
			
		return value;
	}
	
	

}
