package view;

import java.awt.Dimension;

import javax.swing.JFrame;

import controller.AbstractController;
import view.screens.GameScreen;
import view.screens.MenuScreen;
import view.screens.Screen;

/**
 * Class managing views
 * @author Karol Checinski
 *
 */
public class ViewManager extends JFrame{
	
	private static final long serialVersionUID = 9211521794211319047L;
	static public final String MENU_ID = "Menu";
	static public final String JOIN_ID = "Join";
	static public final String CREATE_GAME_ID = "CreateGame";
	static public final String GAME_ID = "Game";
	static public final String GAME_END_ID = "GameEnd";
	
	static public final Dimension FRAME_SIZE = new Dimension(850, 720);
	
	/**
	 * Currently active screen
	 */
	private Screen currentScreen;
	/**
	 * Currently active controller
	 */
	private AbstractController controller;
	
	/**
	 * Default constructor.
	 */
	public ViewManager(){
		prepareViewManager();
	}
	
	/**
	 * Constructor. Sets start screen with given name
	 * @param screenName	Start screen name
	 */
	public ViewManager(String screenName){
		prepareViewManager();
		setScreen(screenName);
	}
	
	private void prepareViewManager(){
		setVisible(true);
	    setResizable(false);
		setTitle("Tanks");
		setSize(FRAME_SIZE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	public void setScreen(String screenName) {
		if(currentScreen!=null){
			currentScreen.setVisible(false);
			remove(currentScreen);
		}
		switch(screenName)
		{
			case MENU_ID: currentScreen = new MenuScreen(controller);System.out.println("viewManager: menuScreen set");break;
			case GAME_ID: currentScreen =  new GameScreen(controller);System.out.println("viewManager: gameScreen set");break;
		}
		add(currentScreen);
	}
	
	public void setController(AbstractController controller){
		if(this.controller != controller){
			this.controller = controller;
			if(currentScreen!=null){
				currentScreen.setController(this.controller);
				System.out.println("Controller setting to frame");
			}
		}
	}
	public Screen getScreen(){
		return currentScreen;
	}
	
	/**
	 * Build current screen GUI
	 */
	public void buildScreenGUI(){
		currentScreen.buildGUI();
		setVisible(true);
	}
	public void updateScreen(){
	    currentScreen.update();
	}

}
