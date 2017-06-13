package ai;

import java.util.ArrayList;

import model.Game;
import ai.Heuristics;
import model.Move;
import util.Vector2;

public class AlphaBeta implements Runnable {
	private Game game;
	private TranspositionTable transpositionTable;
	private Heuristics heuristicFunction;
	private Move nextMove;
	private Move iterNextMove;

	
	private int depth;
	
	private static final int BOUND_DEPTH = 10;
	
	
	public AlphaBeta(Game game, Heuristics heuristicFunction)
	{
		this.game = game;
		this.depth = BOUND_DEPTH;
		this.heuristicFunction = heuristicFunction;
		int size = game.getBoardSize().getSize().x;
		transpositionTable = new TranspositionTable(size);
		nextMove = null;
		
	}
	@Override
	public void run()
	{
		iterAlphaBeta();
	}
	public void setCurrentGame(Game game)
	{
		this.game = game.clone();
	}
	public void iterAlphaBeta ()
	{
		int alpha = Integer.MIN_VALUE;
		int beta = Integer.MAX_VALUE;
		for(int depth = 1; depth <= BOUND_DEPTH; ++depth)
		{
			alphaBeta(game, depth, alpha, beta, MinMaxNode.MAX);
			iterNextMove = nextMove;
		}
	}
	public int alphaBeta (Game game, int depth, int alpha, int beta, MinMaxNode node)
	{
	    if(Thread.currentThread().isInterrupted())
	        return 0;
		int nestedAlpha = alpha;
		int nestedBeta = beta;
		int value = 0;
		Vector2 bestMove = null;
		if(depth == 0 || game.getGameState().isTerminal())
		{
			return (int) (heuristicFunction.heuristicValue(game));
		}
		
		State currentState = transpositionTable.getState(game);
		if(currentState != null && currentState.getDepth() >= depth) // value is good enough
		{
			ValueFlag flag = currentState.getFlag();
			value = currentState.getValue();
			if(flag == ValueFlag.UPPER)
			{
				nestedBeta = Math.min(nestedBeta, value);
			}
			else if(flag ==  ValueFlag.LOWER)
			{
				nestedAlpha = Math.max(nestedAlpha, value);
			}
			else if(flag == ValueFlag.ACCURATE || nestedAlpha >= nestedBeta)
			{
				return value;
			}

		}
		
		int returnValue;
		
		ArrayList<Vector2> nextMoves = game.getMoves();
		if(currentState != null && currentState.getBestMove() != null)
			{
				bestMove = currentState.getBestMove().getPosition();
				if(nextMoves.contains(bestMove))
				{
					nextMoves.remove(bestMove);
					nextMoves.add(0, bestMove); // the move that cause cut off in recent iteration shoulb be checked first
				}
			}
		
		Vector2 besMove = null;
		if( node == MinMaxNode.MAX)
		{
			for(Vector2 move : nextMoves)
			{
				game.makeMove(move);
				value = alphaBeta(game,depth - 1, nestedAlpha, nestedBeta, MinMaxNode.MIN);
				game.undoMove();
				
		        if(Thread.currentThread().isInterrupted())
		            return 0;
		        
				if(value > nestedAlpha)
				{
					besMove=move;
				}
				nestedAlpha = Math.max(nestedAlpha, value);
				if(nestedAlpha >= nestedBeta)
				{
					break; // beta cut off
				}
			}
			returnValue = nestedAlpha; // return alpha (could be lowerbound)
	
		}
		else // MIN node
		{
			for(Vector2 move : nextMoves)
			{
				game.makeMove(move);
				value = alphaBeta(game, depth - 1, nestedAlpha, nestedBeta, MinMaxNode.MAX);
				game.undoMove();
				
		        if(Thread.currentThread().isInterrupted())
		            return 0;
		        
				if(value < nestedBeta)
				{
					besMove=move;
				}
				nestedBeta = Math.min(nestedBeta, value);
				if(nestedAlpha >= nestedBeta)
				{
					break; // alpha cut off 
				}
			}
			returnValue = nestedBeta; // return beta (could be upperbound)
		}
		
		ValueFlag flag;
		
		if(returnValue <= alpha)
			flag = ValueFlag.UPPER; 
		else if( returnValue >= beta)
			flag = ValueFlag.LOWER; 
		else
			flag = ValueFlag.ACCURATE;
		
		Move best = new Move(besMove, game.currentPawn()); // remember for next iteration
		if(besMove != null)
		{
			nextMove = best;
		}
		State s = new State(game.getZobristKey(), depth, returnValue, flag, best);
		
		if(currentState == null || currentState.getDepth() < depth)
		transpositionTable.registerState(game, s);
			
		return returnValue;
	}
	
	public Move getIterNextMove() 
	{
        return iterNextMove;
    }

}
