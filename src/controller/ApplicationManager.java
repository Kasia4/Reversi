package controller;

import view.*;

/**
 * Controller used in menu.
 * @author Karol Checinski
 *
 */
public class ApplicationManager extends AbstractController{
	
	public static void main(String[] args){
		new ApplicationManager(new ViewManager());
	}
	
	//private GameController gameController;
	
	
	public ApplicationManager(ViewManager viewManager){
		super(viewManager);
		launch();
	}
	
	@Override
	public void launch(){
		
		this.viewManager.setScreen(ViewManager.MENU_ID);
		this.viewManager.setController(this);
		this.viewManager.buildScreenGUI();
	}
	
	public void startGame(){
	    this.viewManager.setScreen(ViewManager.GAME_ID);
	    this.viewManager.setController(this);
	    this.viewManager.buildScreenGUI();
	}
	
	/**
	 * Execute when New Game button in menu has pressed. Switch control to GameController
	 */
//	public void createGameOption(){
//			gameController = new GameController(viewManager);
//			gameController.launch();
//			gameController.setApplicationManager(this);
//	}
	
	/**
	 * Exit application
	 */
	public void exit(){
		System.exit(0);
	}
	
	
}
