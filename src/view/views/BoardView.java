package view.views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Board;
import model.Field;
import model.Pawn;
import util.Vector2;

/**
 * View of game board
 * @author Kokos
 *
 */
public class BoardView extends ApplicationManagerView{

    private static final long serialVersionUID = 1L;
    JLabel title;
    Board board;

    public BoardView(Board board){
        this.board = board;
    }
    @Override
    public void buildGUI() {
        setBackground(new Color(0, 0.6f, 0));
        setOpaque(true);
        setLayout(new GridLayout(8, 8, 10, 10));
        for(int y = 0; y < 8; y++)
            for(int x = 0; x < 8; x++)
            add(new RectDraw(findColor(new Vector2(x,y))));
        setVisible(true);
    }
    
    private static class RectDraw extends JPanel {
        private static final long serialVersionUID = 1L;
        Color color;
        private RectDraw(Color color){
            this.color = color;
        }
        protected void paintComponent(Graphics g) {
          super.paintComponent(g);
          g.setColor(color);
          g.fillRect(0, 0, 640 / 8 - 10, 640 / 8 - 10);
          g.setColor(Color.BLACK);
          g.drawRect(0, 0, 640 / 8 - 10, 640 / 8 - 10);
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
        else
        {
            if(board.canMove(Pawn.WHITE, pos))
                return new Color(56/255f, 188/255f, 0/255f);
            else
                return new Color(0, 0.6f, 0);      
        }
    }
}
