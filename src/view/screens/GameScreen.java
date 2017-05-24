package view.screens;

import java.awt.GridLayout;

import controller.AbstractController;
import view.views.BoardView;

/**
 * Game Screen
 * @author Kokos
 *
 */
public class GameScreen extends Screen{

    private static final long serialVersionUID = 1L;
    BoardView boardView;

    public GameScreen(AbstractController controller) {
        super(controller);
    }

    @Override
    public void buildGUI() {
        setLayout(new GridLayout(1, 1));
        boardView = new BoardView();
        addView(boardView);
        boardView.buildGUI();
        setVisible(true);
    }

}
