package view.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BoardMouseAdapter;
import model.Board;
import model.Field;
import model.Game;
import util.Vector2;

/**
 * View of game board
 * @author Kokos
 *
 */
public class BoardView extends GameManagerView{

    private static final long serialVersionUID = 1L;
    JLabel title;
    Game game;
    Board board;
    BoardMouseAdapter boardMouseAdapter;

    Vector2 clickedField = null;
    
    Vector<RectDraw> fields;
    
    public BoardView(Game game){
        this.game = game;
        this.fields = new Vector<RectDraw>();
        this.board = game.getBoard();
        boardMouseAdapter = new BoardMouseAdapter(this);
    }
    
    @Override
    public void buildGUI() {
        setBackground(new Color(0, 0.6f, 0));
        setOpaque(true);
        setLayout(new GridLayout(board.getBoardSize().x, board.getBoardSize().y, 5, 5));
        for(int y = 0; y < board.getBoardSize().y; y++)
            for(int x = 0; x < board.getBoardSize().x; x++)
                add(new RectDraw(findColor(new Vector2(x,y)), new Vector2(x,y)));
        setVisible(true);
    }
    
    public class RectDraw extends JPanel {
        private static final long serialVersionUID = 1L;
        Color color;
        Vector2 position;
        private RectDraw(Color color, Vector2 position){
            this.position = position;
            this.color = color;
            fields.add(this);
            this.addMouseListener(boardMouseAdapter);
        }
        public void paintComponent(Graphics g) {
          super.paintComponent(g);
          g.setColor(color);
          g.fillRect(0, 0, 640 / board.getBoardSize().x - 6, 640 / board.getBoardSize().y - 6);
          g.setColor(Color.BLACK);
          g.drawRect(0, 0, 640 / board.getBoardSize().x - 6, 640 / board.getBoardSize().y - 6);
        }
        public Vector2 getPosition(){
            return position;
        }
    }
    
    /**
     * Return color of field
     * @param pos position of field
     * @return  color of searched field
     */
    private Color findColor(Vector2 pos){
        if(board.getField(pos) == Field.BLACK)
           return Color.BLACK;
        else if (board.getField(pos) == Field.WHITE)
           return Color.WHITE;
        else{
        	if(board.canMove(game.getGameState().getPawn(), pos))
        	{
                return new Color(56/255f, 188/255f, 0/255f);
        	} else
                return new Color(0, 0.6f, 0);
        }
    }
    
    synchronized public void resetMove()
    {
    	clickedField = null;
    }
    synchronized public void sendMove(Vector2 position){
    	clickedField = position;
    }
    synchronized public Vector2 getMove()
    {
    	return clickedField;
    }
    
    public void update(){
    	setVisible(false);
        removeAll();        
        for(int y = 0; y < board.getBoardSize().y; y++)
            for(int x = 0; x < board.getBoardSize().x; x++)
                add(new RectDraw(findColor(new Vector2(x,y)), new Vector2(x,y)));

        repaint();
        setVisible(true);

    }
}
