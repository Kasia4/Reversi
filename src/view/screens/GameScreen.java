package view.screens;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.AbstractController;
import model.Board;
import model.BoardSize;
import model.Move;
import model.Pawn;
import util.Vector2;
import view.views.BoardView;
import view.views.ControlPanelView;

/**
 * Game Screen
 * @author Kokos
 *
 */
public class GameScreen extends Screen{

    private static final long serialVersionUID = 1L;
    BoardView boardView;
    ControlPanelView controlPanel;
    
    /**
     * TYLKO DO TESTÓW VIEW - DO USUNIECIA!!!!!
     * @author Kokos
     */
    Board board;
    private int sizeOfBoard;

    public GameScreen(AbstractController controller) {
        super(controller);
        board = new Board(BoardSize.SMALL);
        sizeOfBoard = 8;
        board.executeMove(new Move(new Vector2(4,2), Pawn.BLACK));
    }

    @Override
    public void buildGUI() {
        setLayout(null);
        setBackground(new Color(0, 0.6f, 0));
        addBoard();
        addTopLabel();
        addLeftLabel();
        addControlPanel();
        boardView.buildGUI();
        controlPanel.buildGUI();
        System.out.println(board.heuristicTest());
        setVisible(true);
    }
    
    private void addBoard(){
        boardView = new BoardView(board);
        boardView.setBounds(30, 30, 640, 640);
        addView(boardView);
        add(boardView);
    }
    private void addControlPanel(){
        controlPanel = new ControlPanelView(board);
        controlPanel.setBounds(690, 200, 140, 300);
        addView(controlPanel);
        add(controlPanel);
    }
    
    private void addTopLabel(){
        JPanel topLabels = new JPanel(new GridLayout(1, sizeOfBoard));
        for(int i = 0; i < sizeOfBoard; i++)
            topLabels.add(new JLabel((i + 1) + "", SwingConstants.CENTER));
        
        topLabels.setBounds(30, 0, 640, 30);
        topLabels.setBackground(new Color(0, 0.6f, 0));
        add(topLabels);
    }
    
    private void addLeftLabel(){
        JPanel leftLabels = new JPanel(new GridLayout(sizeOfBoard, 1));
        for(int i = 0; i < sizeOfBoard; i++)
            leftLabels.add(new JLabel((i + 1) + "", SwingConstants.CENTER));
        
        leftLabels.setBounds(0, 30, 30, 640);
        leftLabels.setBackground(new Color(0, 0.6f, 0));
        add(leftLabels);
    }

}
