package model;

import java.util.ArrayList;

import util.Vector2;
/**
 * Is responsable for heuristisc
 * @author Kokos
 *
 */
public class Heuristics {
    
    HeuristicParametersReader heuristicParametersReader;
    
    float[][] weightMatrix;
    
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
        float sum = 0;
        //Player player = new Player(Pawn.WHITE);//tylko do test�w
        Pawn pawn = Pawn.WHITE;
        int b = 0;
        float v = 4.1f; // Mobility weight
        for(int x = 0; x < board.getBoardSize().x; x++)
            for(int y = 0; y < board.getBoardSize().y; y++){
                Field current = board.getField(new Vector2(x,y));
                if(current == pawn.color)
                    b = 1;
                else if(current == pawn.opposite)
                    b = -1;
                else continue;
                
                    sum += b * getWeightOfField(new Vector2(x,y));
                    System.out.println( b * getWeightOfField(new Vector2(x,y)));
            }
        float mobility = 0;
        
        ArrayList<Vector2> availableFields = board.getAvailableFields(pawn);
        for(Vector2 field : availableFields){
            float value = 1;
            if(getWeightOfField(field) > 0);
                value = getWeightOfField(field);
            mobility += value;
        }
        
        sum += v * mobility;
        System.out.println(v + " " + (v * board.availableFieldsNumber(pawn)));
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
    
}
