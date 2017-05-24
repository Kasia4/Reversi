package view.views;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * View of game board
 * @author Kokos
 *
 */
public class BoardView extends ApplicationManagerView{

    private static final long serialVersionUID = 1L;
    JLabel title;

    @Override
    public void buildGUI() {
        System.out.println("asdad");
        title = new JLabel("REVERSI");
        add(title);
        title.setText("dsadaadsddsaddsa");
        add(new JButton("test"));
        setBackground(Color.GREEN);
        setOpaque(true);
        setVisible(true);
    }

}
