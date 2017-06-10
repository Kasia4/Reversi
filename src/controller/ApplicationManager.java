package controller;

import model.BoardSize;
import model.HeuristicParametersReader;
import model.Game;

import view.ViewManager;

/**
 * Controller used in menu.
 * @author Karol Checinski
 *
 */
public class ApplicationManager extends AbstractController{
	
    GameController gameController;
    
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
	
	public void createGame(BoardSize boardSize, PlayerType playerWhite, PlayerType playerBlack){
	    gameController = new GameController(viewManager, boardSize, playerWhite, playerBlack);
	    gameController.launch();
	    gameController.setApplicationManager(this);
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
