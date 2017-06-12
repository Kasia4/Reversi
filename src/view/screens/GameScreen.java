package view.screens;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import ai.Heuristics;
import controller.AbstractController;
import controller.GameController;
import model.Board;
import model.BoardSize;
import model.Game;
import model.GameState;
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

    Game game;
    Board board;
    Heuristics heu;
    private int sizeOfBoard;

    public GameScreen(AbstractController controller) {
        super(controller);
    }

    private void init(){
        this.game = ((GameController)controller).getGame();
        board = game.getBoard();
        heu = new Heuristics(BoardSize.SMALL);
        sizeOfBoard = board.getBoardSize().x;
    }
    @Override
    public void buildGUI() {
        init();
        setLayout(null);
        setBackground(new Color(0, 0.6f, 0));
        addBoard();
        addTopLabel();
        addLeftLabel();
        addControlPanel();
        boardView.buildGUI();
        controlPanel.buildGUI();
        JButton debugBtn = new JButton("Siemano");
        debugBtn.setBounds(690, 550, 140, 50);
        add(debugBtn);
        debugBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                game.makeMove(new Vector2(-1,-1));
                heu.setPlayerPawn(Pawn.WHITE);
                System.out.println(heu.heuristicTest(board));
                boardView.update();
            }
        });
        setVisible(true);
    }
    
    public void update(){
        boardView.update();
        controlPanel.update();
        if(game.getGameState().isTerminal()){
            showResult();
        }
    }
    
    private void addBoard(){
        boardView = new BoardView(game);
        boardView.setBounds(30, 30, 640, 640);
        addView(boardView);
        add(boardView);
    }
    private void addControlPanel(){
        controlPanel = new ControlPanelView(game);
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
    private void showResult(){
        String msg;
        if(game.getGameState() == GameState.DRAW)
           msg = "Draw!";
        else
           msg = "Player " + game.getGameState().getPawn() + " won";
        
        JOptionPane.showMessageDialog(new JFrame(),
                msg,
                "End of game",
                JOptionPane.PLAIN_MESSAGE);
    }
}
