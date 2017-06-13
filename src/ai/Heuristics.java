package ai;

import java.util.ArrayList;

import model.Board;
import model.BoardSize;
import model.Field;
import model.Game;
import model.Pawn;
import util.Vector2;
/**
 * Is responsable for heuristisc
 * @author Kokos
 *
 */
public class Heuristics {
    
    HeuristicParametersReader heuristicParametersReader;
    private float mobilityWeight;
    
    float[][] weightMatrix;
    Pawn playerPawn;
    
    public Heuristics(BoardSize boardSize){
        HeuristicParametersReader heuristicParametersReader = new HeuristicParametersReader(boardSize);
        weightMatrix = heuristicParametersReader.getWeightMatrix();
        mobilityWeight = heuristicParametersReader.getMobilityFactor();
    }
    
    /**
     * @author Kokos
     * @return result of heuristic function
     */

    public float heuristicValue(Game game){
        Board board = game.getBoard();
        float sum = 0;
        int whosTurn = 0;
        
        for(int y = 0; y < board.getBoardSize().y; y++)
            for(int x = 0; x < board.getBoardSize().x; x++){
                Field current = board.getField(new Vector2(x,y));
                if(current == playerPawn.color())
                    whosTurn = 1;
                else if(current == playerPawn.opposite())
                    whosTurn = -1;
                else continue;
                
                    sum += whosTurn * getWeightOfField(new Vector2(x,y));
            }
        
        float mobility = 0;
        ArrayList<Vector2> availableFields = board.getAvailableFields(playerPawn);
        for(Vector2 field : availableFields){
            float value = 1;
            if(getWeightOfField(field) > 0)
                value = getWeightOfField(field);
            mobility += value;
        }
        
        sum += mobilityWeight * mobility;
        return sum;
    }
    
    /**
     * Takes value from matrix and returns it
     * @param vec position that is needed to check
     * @return return value for this position
     */
    private float getWeightOfField(Vector2 vec){
    	return weightMatrix[vec.x][vec.y];    
    }
    
    public void setPlayerPawn(Pawn pawn) {
    	this.playerPawn = pawn;
    }

}
