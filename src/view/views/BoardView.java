package view.views;

import java.awt.Color;

/**
 * View of game board
 * @author Kokos
 *
 */
public class BoardView extends ApplicationManagerView{

    private static final long serialVersionUID = 1L;

    @Override
    public void buildGUI() {
        System.out.println("gitara");
        setBackground(Color.GREEN);
        setOpaque(true);
        setVisible(true);
    }

}
