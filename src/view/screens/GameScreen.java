package view.screens;

import java.awt.GridLayout;

import controller.AbstractController;
import model.Board;
import model.BoardSize;
import model.Move;
import model.Pawn;
import util.Vector2;
import view.views.BoardView;

/**
 * Game Screen
 * @author Kokos
 *
 */
public class GameScreen extends Screen{

    private static final long serialVersionUID = 1L;
    BoardView boardView;
    
    /**
     * TYLKO DO TESTÓW VIEW - DO USUNIECIA!!!!!
     * @author Kokos
     */
    Board board;

    public GameScreen(AbstractController controller) {
        super(controller);
        board = new Board(BoardSize.SMALL);
        //board.executeMove(new Move(new Vector2(4,2), Pawn.BLACK));
    }

    @Override
    public void buildGUI() {
        setLayout(new GridLayout(1, 1));
        boardView = new BoardView(board);
        addView(boardView);
        add(boardView);
        boardView.buildGUI();
        setVisible(true);
    }

}
