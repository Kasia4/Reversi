package view.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;

import model.Board;
import model.Field;
import model.Game;

public class ControlPanelView extends GameManagerView{

    private static final long serialVersionUID = 1L;
    JLabel white, whiteScore, black, blackScore, turn, pawnTurn;
    static final Dimension BTN_SIZE = new Dimension (200, 40);
    private Game game;
    private Board board;
    
    public ControlPanelView(Game game) {
        this.game = game;
        this.board = game.getBoard();
    }
    
    @Override
    public void buildGUI() {
        setBackground(new Color(196/255f, 196/255f, 196/255f));
        white = new JLabel("White:", JLabel.CENTER);
        white.setMaximumSize(BTN_SIZE);
        whiteScore = new JLabel(board.getFieldsNumber(Field.WHITE) + "", JLabel.CENTER);
        whiteScore.setMaximumSize(BTN_SIZE);
        
        black = new JLabel("Black:", JLabel.CENTER);
        black.setMaximumSize(BTN_SIZE);
        blackScore = new JLabel(board.getFieldsNumber(Field.BLACK) + "", JLabel.CENTER);
        blackScore.setMaximumSize(BTN_SIZE);

        turn = new JLabel("Turn: ", JLabel.CENTER);
        turn.setMaximumSize(BTN_SIZE);
        /**
         * TODO change it
         */
        pawnTurn = new JLabel("BLACK", JLabel.CENTER);
        pawnTurn.setMaximumSize(BTN_SIZE);
            
        setLayout(new GridLayout(3,2));
        
        add(white);
        add(whiteScore);
        
        add(black);
        add(blackScore);
        
        add(turn);
        add(pawnTurn);     
    }
    public void update(){
        whiteScore.setText(board.getFieldsNumber(Field.WHITE) + "");
        blackScore.setText(board.getFieldsNumber(Field.BLACK) + "");
        pawnTurn.setText(game.getGameState().getPawn() + "");
    }

}
