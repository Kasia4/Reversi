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
    JLabel white, whiteScore, black, blackScore, turn, pawnTurn, yourPawnLable, yourPawn;
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
        
        yourPawnLable = new JLabel("Your pawn: ", JLabel.CENTER);
        yourPawnLable.setMaximumSize(BTN_SIZE);
        yourPawn = new JLabel("BLACK", JLabel.CENTER);
        yourPawn.setMaximumSize(BTN_SIZE);
        
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

        pawnTurn = new JLabel("BLACK", JLabel.CENTER);
        pawnTurn.setMaximumSize(BTN_SIZE);
            
        setLayout(new GridLayout(4,2));
        
        add(yourPawnLable);
        add(yourPawn);
        
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
    public void changePawnColor(){
        if(yourPawn.getText().equals("BLACK"))
            yourPawn.setText("WHITE");
        else
            yourPawn.setText("BLACK");
    }

}
