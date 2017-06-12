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
    private float cornerWallWeight;
    
    float[][] weightMatrix;
    Pawn playerPawn;
    
    public Heuristics(BoardSize boardSize){
        HeuristicParametersReader heuristicParametersReader = new HeuristicParametersReader(boardSize);
        weightMatrix = heuristicParametersReader.getWeightMatrix();
        mobilityWeight = 0.5f;
        cornerWallWeight = 1;

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

    public float heuristicTest(Game game){
        Board board = game.getBoard();
        float sum = 0;
        int whosTurn = 0;
        
        for(int x = 0; x < board.getBoardSize().x; x++)
            for(int y = 0; y < board.getBoardSize().y; y++){
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
        
       
       // System.out.println("E(s) without mobility: " + sum);
        sum += mobilityWeight * mobility;
        //System.out.println("Mobility: " + mobility);


     Corner[] corners = setCorners(board);
//        
//        int cornerWall = 0;
//        for(Corner c : corners){
//            int our = -1;
//            int counter = 0;
//            if(c.getField() == playerPawn.color()){
//                our = 1;
//            }
//            else if(c.getField() == Field.EMPTY)
//                continue;
//            
//            Vector2 current = c.getPosition();
//            while(board.getField(current) == c.getField()){
//                current = Vector2.add(current, c.getVertical());
//                counter++;
//            }
//            current = c.getPosition();
//            while(board.getField(current) == c.getField()){
//                current = Vector2.add(current, c.getHorizonal());
//                counter++;
//            }
//            cornerWall += our * counter;
//        }

//        int cornerWall = 0;
//        for(Corner c : corners){
//            int our = -1;
//            int counter = 0;
//            if(c.getField() == playerPawn.color()){
//                our = 1;
//            }
//            else if(c.getField() == Field.EMPTY)
//                continue;
//            
//            Vector2 current = c.getPosition();
//            while(board.getField(current) == c.getField() && (current.x < board.getBoardSize().x)){
//                current = Vector2.add(current, c.getVertical());
//                counter++;
//            }
//            current = c.getPosition();
//            while(board.getField(current) == c.getField() && (current.x < board.getBoardSize().y)){
//                current = Vector2.add(current, c.getHorizonal());
//                counter++;
//            }
//            cornerWall += our * counter;
//        }
        
       // System.out.println("Position strategy: " + sum);
        
       // System.out.println("Corner wall: " + cornerWall);
       // System.out.println("Corner wall with weight: " + (cornerWallWeight * cornerWall) + " weight: " + cornerWallWeight);
        //sum += cornerWallWeight * cornerWall;
        
        
       // System.out.println("Mobility(ReLU): " + mobility);
       // System.out.println("Mobility with weigth: " + mobilityWeight * mobility + " weight: " + mobilityWeight);
        sum += mobilityWeight * mobility;
       
        board.printOut();
        System.out.println("E(s) = " + sum);

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
    private Corner[] setCorners(Board board){
        Corner[] corners = new Corner[4];
        
        Vector2 NW = new Vector2(0, 0);
        Vector2 NE = new Vector2(board.getBoardSize().x - 1, 0);
        Vector2 SE = new Vector2(board.getBoardSize().x - 1, board.getBoardSize().y - 1);
        Vector2 SW = new Vector2(0, board.getBoardSize().x - 1);
        
        corners[0] = new Corner(board.getField(NW) , NW);
        corners[1] = new Corner(board.getField(NE) , NE);
        corners[2] = new Corner(board.getField(SE) , SE);
        corners[3] = new Corner(board.getField(SW) , SW);
        return corners;
    }
}
