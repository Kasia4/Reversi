package view.screens;


import java.awt.*;

import controller.AbstractController;
import view.views.*;

/**
 * Main menu screen
 * @author Karol Checinski
 *
 */
public class MenuScreen extends Screen {
	
	private static final long serialVersionUID = 7587123307067469462L;
	MenuView menuView;

	public MenuScreen(AbstractController controller) {
		super(controller);
	}
	
	public void buildGUI()
	{
		menuView = new MenuView();
		addView(menuView);
		
		setLayout(new GridBagLayout());
        add(menuView, new GridBagConstraints());
        menuView.buildGUI();

        setVisible(true);
	}
	public void update(){};
}
