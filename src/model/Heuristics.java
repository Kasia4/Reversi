package model;

import controller.Player;
import util.Matrix;
import util.Vector2;
/**
 * Is responsable for heuristisc
 * @author Kokos
 *
 */
public class Heuristics {
  
    private Matrix<Float> smallMatrix, mediumMatrix, largeMatrix;
    
    private BoardSize boardSize;
    
    float sA = 53.15f;
    float sB = -32.97f;
    float sC = -43.33f;
    float sD = 24.61f;
    float sE = -26.26f;
    float sF = 1.04f;
    private float[][] smallValueMatrix = new float[][]{
        {sA, sB, sD, sD, sD, sD, sB, sA},
        {sB, sC, sE, sE, sE, sE, sC, sB},
        {sD, sE, sF, sF, sF, sF, sE, sD},
        {sD, sE, sF, sF, sF, sF, sE, sD},
        {sD, sE, sF, sF, sF, sF, sE, sD},
        {sD, sE, sF, sF, sF, sF, sE, sD},
        {sB, sC, sE, sE, sE, sE, sC, sB},
        {sA, sB, sD, sD, sD, sD, sB, sA},
        }; 
        
    float mA = 0.0f;
    float mB = 0.0f;
    float mC = 0.0f;
    float mD = 0.0f;
    float mE = 0.0f;
    float mF = 0.0f;
    private float[][] mediumValueMatrix = new float[][]{
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        }; 
    float lA = 0.0f;
    float lB = 0.0f;
    float lC = 0.0f;
    float lD = 0.0f;
    float lE = 0.0f;
    float lF = 0.0f;
    private float[][] largeValueMatrix = new float[][]{
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
        };     
        
    
    public Heuristics(BoardSize boardSize){
        this.boardSize = boardSize;
        setMatrix();

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
    // game as input not board
    public float heuristicTest(Board board){
        float sum = 0;
        //Player player = new Player(Pawn.WHITE);//tylko do test�w
        Pawn pawn = Pawn.WHITE;
        int b = 0;
        float v = 4.1f;
        for(int x = 0; x < board.getBoardSize().x; x++)
            for(int y = 0; y < board.getBoardSize().y; y++){
                Field current = board.getField(new Vector2(x,y));
                if(current == pawn.color)
                    b = 1;
                else if(current == pawn.opposite)
                    b = -1;
                else continue;
                
                    sum += b * getValueMatrix(new Vector2(x,y));
            }
        sum += v * board.availableFieldsNumber(pawn);
        return sum;
    }
    
    /**
     * Creates matrix with values
     */
    private void setMatrix(){
        if(boardSize == BoardSize.SMALL){
            smallMatrix = new Matrix<Float>(new Vector2(BoardSize.SMALL.size, BoardSize.SMALL.size));
            for(int x = 0; x < smallMatrix.getSize().x; x++)
                for(int y = 0; y < smallMatrix.getSize().y; y++){
                    smallMatrix.setField(new Vector2(x,y), getValueInTable(new Vector2(x,y)));
                }
        }
        else if(boardSize == BoardSize.MEDIUM){
            mediumMatrix = new Matrix<Float>(new Vector2(BoardSize.MEDIUM.size, BoardSize.MEDIUM.size));
            for(int x = 0; x < mediumMatrix.getSize().x; x++)
                for(int y = 0; y < mediumMatrix.getSize().y; y++){
                    mediumMatrix.setField(new Vector2(x,y), getValueInTable(new Vector2(x,y)));
                }
        }
        else {
            largeMatrix = new Matrix<Float>(new Vector2(BoardSize.LARGE.size, BoardSize.LARGE.size));
            for(int x = 0; x < largeMatrix.getSize().x; x++)
                for(int y = 0; y < largeMatrix.getSize().y; y++){
                    largeMatrix.setField(new Vector2(x,y), getValueInTable(new Vector2(x,y)));
                }
        }
    }
    
    /**
     * Gives value on this position 
     * @param vec position
     * @return value
     */
    private float getValueMatrix(Vector2 vec){    
        if(boardSize == BoardSize.SMALL)
            return smallMatrix.getField(vec);    
        else if(boardSize == BoardSize.MEDIUM)
            return mediumMatrix.getField(vec);
        else 
            return largeMatrix.getField(vec);
    }
    /**
     * Prints out in console smallMatrix
     */
    public void printMatrix(){
        if(boardSize == BoardSize.SMALL)
            smallMatrix.printOut();    
        else if(boardSize == BoardSize.MEDIUM)
            mediumMatrix.printOut();
        else 
            largeMatrix.printOut();
    }
    
    /**
     * Takes value from table and returns it
     * @param vec position that is needed to check
     * @return return value for this position
     */
    private float getValueInTable(Vector2 vec){
        if(boardSize == BoardSize.SMALL)
            return smallValueMatrix[vec.x][vec.y];    
        else if(boardSize == BoardSize.MEDIUM)
            return mediumValueMatrix[vec.x][vec.y];
        else 
            return largeValueMatrix[vec.x][vec.y];
    }
    
}
