package ai;

import java.util.ArrayList;

import model.Game;
import ai.Heuristics;
import model.Move;
import util.Vector2;

public class AlphaBeta {
	private Game game;
	private TranspositionTable transpositionTable;
	private Heuristics heuristicFunction;
	private Move nextMove;
	private int depth;
	
	private static final int BOUND_DEPTH = 30;
	
	
	public AlphaBeta(Game game, Heuristics heuristicFunction)
	{
		this.game = game;
		this.depth = BOUND_DEPTH;
		this.heuristicFunction = heuristicFunction;
		int size = game.getBoardSize().getSize().x;
		transpositionTable = new TranspositionTable(size);
		nextMove = null;
		
	}
	
	public void setCurrentGame(Game game)
	{
		//this.game = game.clone(); remember about it
		this.game = game;
	}
	
	public int alphaBeta (Game game, int depth, int alpha, int beta, MinMaxNode node)
	{
		int nestedAlpha = alpha;
		int nestedBeta = beta;
		int value = 0;
		Vector2 bestMove = null;
		if(depth == 0 || game.getGameState().isTerminal())
			return (int) (heuristicFunction.heuristicTest(game));
		
		State currentState = transpositionTable.getState(game);
		if(currentState != null && currentState.getDepth() >= depth) // value is good enough
		{
			ValueFlag flag = currentState.getFlag();
			value = currentState.getValue();
			if(flag == ValueFlag.UPPER)
				nestedBeta = Math.min(nestedBeta, value);
			else if(flag ==  ValueFlag.LOWER)
				nestedAlpha = Math.max(nestedAlpha, value);
			else
				nestedAlpha = nestedBeta = value;
			if(flag == ValueFlag.ACCURATE || nestedAlpha >= nestedBeta)
				return value;
			
		}
		
		// ADD USE OF BESTMOVE FROM STATE FROM TRANSPOSITION TABLE !!!
		int returnValue;
		Vector2 besMove;
		ArrayList<Vector2> nextMoves = game.getMoves();
		if(currentState != null && currentState.getBestMove() != null)
			{
				besMove = currentState.getBestMove().getPosition();
				if(nextMoves.contains(besMove))
				{
					nextMoves.remove(besMove);
					nextMoves.add(0, besMove); // the move that cause cut off in recent iteration shoulb be checked first
				}
			}
		
		
		if( node == MinMaxNode.MAX)
		{
			for(Vector2 move : nextMoves){
				game.makeMove(move);
				value = alphaBeta(game,depth - 1, nestedAlpha, nestedBeta, MinMaxNode.MIN);
				game.undoMove();
				if(value > nestedAlpha)
					besMove=move;
				nestedAlpha = Math.max(nestedAlpha, value);
				if(nestedAlpha >= nestedBeta)
					break; // beta cut off
			}
			returnValue = nestedAlpha; // return alpha (could be lowerbound)
	
		}
		else // MIN node
		{
			for(Vector2 move : nextMoves){
				game.makeMove(move);
				value = alphaBeta(game, depth - 1, nestedAlpha, nestedBeta, MinMaxNode.MAX);
				game.undoMove();
				if(value < nestedBeta)
					bestMove = move;
				nestedBeta = Math.min(nestedBeta, value);
				if(nestedAlpha >= nestedBeta)
					break; // alpha cut off 
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
		
		Move best = new Move(bestMove, game.currentPawn()); // remember for next iteration
		if(bestMove != null)
			nextMove = best;
		State s = new State(game.getZobristKey(), depth, returnValue, flag, best);
		
		if(currentState == null || currentState.getDepth() < depth)
		transpositionTable.registerState(game, s);
			
		return returnValue;
	}
	
	

}
