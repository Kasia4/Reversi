package ai;

import java.util.ArrayList;

import model.Board;
import model.BoardSize;
import model.Field;
import model.Pawn;
import util.Vector2;
/**
 * Is responsable for heuristisc
 * @author Kokos
 *
 */
public class Heuristics {
    
    HeuristicParametersReader heuristicParametersReader;
    
    float[][] weightMatrix;
    Pawn playerPawn;
    
    public Heuristics(BoardSize boardSize){
        HeuristicParametersReader heuristicParametersReader = new HeuristicParametersReader(boardSize);
        weightMatrix = heuristicParametersReader.getWeightMatrix();

    }
    /**
     * pr�buje napisa� funkcje heurystyczn� na podstawie http://sequoia.ict.pwr.wroc.pl/~witold/aiuwr/2001_projekty/reversi/
     * narazie w board bo nie wiem jak rozwia�emy jaki panel si� tym zajmuje
     * Zak�adam �e funkja dzia�a po prostu na jakim� stanie planszy.
     * Narazie tylko dla 8x8
     * 
     * Function that returns value of heuristic function where result depends on place where pawns are and number of possible moves
     * 
     * @author Kokos
     * @return result of heuristic function
     */
    public float heuristicTest(Board board){
    	float[][] test = new float[8][8];
        float sum = 0;
        int b = 0;
        float v = 4.1f; // Mobility weight
        for(int x = 0; x < board.getBoardSize().x; x++)
            for(int y = 0; y < board.getBoardSize().y; y++){
                Field current = board.getField(new Vector2(x,y));
                if(current == playerPawn.color())
                    b = 1;
                else if(current == playerPawn.opposite())
                    b = -1;
                else continue;
                	test[x][y] = b * getWeightOfField(new Vector2(x,y));
                    sum += b * getWeightOfField(new Vector2(x,y));
                    //System.out.println( b * getWeightOfField(new Vector2(x,y)));
            }
        float mobility = 0;
        
        ArrayList<Vector2> availableFields = board.getAvailableFields(playerPawn);
        for(Vector2 field : availableFields){
            float value = 1;
            if(getWeightOfField(field) > 0)
                value = getWeightOfField(field);
            mobility += value;
        }
        
        sum += v * mobility;
        for(int y = 0; y < 8; ++y)
        {
        	String line = "";
        	for(int x = 0; x < 8; ++x)
        	{
        		line += test[x][y] + "\t";
        	}
        	System.out.println(line);
        	line = "";
        }
        System.out.println("Mobility:  " + mobility);
        //System.out.println(v + " " + (v * board.availableFieldsNumber(playerPawn)));
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
