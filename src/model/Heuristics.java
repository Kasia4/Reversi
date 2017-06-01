package model;

import util.Matrix;
import util.Vector2;
/**
 * Is responsable for heuristisc
 * @author Kokos
 *
 */
public class Heuristics {
  
    private Matrix<Float> smallMatrix;
    
    public Heuristics(){
        setSmallMatrix();
    }
    /**
     * próbuje napisaæ funkcje heurystyczn¹ na podstawie http://sequoia.ict.pwr.wroc.pl/~witold/aiuwr/2001_projekty/reversi/
     * narazie w board bo nie wiem jak rozwia¿emy jaki panel siê tym zajmuje
     * Zak³adam ¿e funkja dzia³a po prostu na jakimœ stanie planszy.
     * Narazie tylko dla 8x8
     * 
     * Function that returns value of heuristic function where result depends on place where pawns are and number of possible moves
     * 
     * @author Kokos
     * @return result of heuristic function
     */
    public float heuristicTest(Board board){
        float sum = 0;
        Player player = new Player(Pawn.WHITE);//tylko do testów
        int b = 0;
        float v = 4.1f;
        for(int x = 0; x < board.getBoardSize().x; x++)
            for(int y = 0; y < board.getBoardSize().y; y++){
                Field current = board.getField(new Vector2(x,y));
                if(current == player.getPawn().color)
                    b = 1;
                else if(current == player.getPawn().opposite)
                    b = -1;
                else continue;
          
                sum += b * getValueSmallMatrix(new Vector2(x,y));//TODO change if difrent board
            }
        sum += v * board.availableFieldsNumber(player.getPawn());
        return sum;
    }
    
    
    private void setSmallMatrix(){
        smallMatrix = new Matrix<Float>(new Vector2(BoardSize.SMALL.size, BoardSize.SMALL.size));
        for(int x = 0; x < smallMatrix.getSize().x; x++)
            for(int y = 0; y < smallMatrix.getSize().y; y++){
                smallMatrix.setField(new Vector2(x,y), getValueOnSmallBoard(new Vector2(x,y)));
            }
    }
    private float getValueSmallMatrix(Vector2 vec){
        return smallMatrix.getField(vec);
    }
    public void printSmallMatrix(){
        smallMatrix.printOut();
    }
    /**
     * wartoœci brane z tamtej storny
     * @param vec position that is needed to check
     * @return return value for whis position
     */
    private float getValueOnSmallBoard(Vector2 vec){
        float a = 53.15f;
        float b = -32.97f;
        float c = -43.33f;
        float d = 24.61f;
        float e = -26.26f;
        float f = 1.04f;
        int x = vec.x;
        int y = vec.y;
        
        if(((x >= 2 && x <=5) && (y == 1 || y == 6)) || (((y >= 2 && y <= 5) && (x == 1 || x == 6))))
            return e;
        else if((x == 1 || x == 6) && (y == 1 || y == 6))
            return c;
        else if(((x >= 2 && x <=5) && (y == 0 || y == 7)) || (((y >= 2 && y <= 5) && (x == 0 || x == 7))))
            return d;
        else if((x == 1 || x == 6) && (y == 0 || y == 7) || (y == 1 || y == 6) && (x == 0 || x == 7))
            return b;
        else if((x == 0 || x == 7) && (y == 0 || y == 7))
            return a;
        else
            return f;
    }
}
