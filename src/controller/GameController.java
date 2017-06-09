package controller;

import model.BoardSize;
import model.Game;
import util.Vector2;
import view.ViewManager;


public class GameController extends AbstractController{

    ApplicationManager appManager;
    Game game;


    public GameController(ViewManager viewManager) {
        super(viewManager);     
        game = new Game(BoardSize.SMALL);
    }

    @Override
    public void launch() {
        viewManager.setScreen(ViewManager.GAME_ID);
        viewManager.setController(this);
        viewManager.buildScreenGUI();
    }
    public void setApplicationManager(ApplicationManager appManager){
        this.appManager = appManager;
    }
    public void sendMove(Vector2 position){
        if(game.makeMove(position)){
            appManager.viewManager.updateScreen();
        }
    }
    public Game getGame() {
        return game;
    }
}
