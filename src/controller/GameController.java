package controller;

import model.BoardSize;
import model.Game;
import model.Pawn;
import util.Vector2;
import view.ViewManager;


public class GameController extends AbstractController implements Runnable{

    ApplicationManager appManager;
    Game game;
    PlayerType playerType[] = new PlayerType[2];
    Player player[] = new Player[2];


    public GameController(ViewManager viewManager, BoardSize boardSize, PlayerType whiteType, PlayerType blackType) {
        super(viewManager);     
        game = new Game(boardSize);
        playerType[Pawn.WHITE.id()] = whiteType;
        playerType[Pawn.BLACK.id()] = blackType;
    }

    @Override
    public void launch() {
        viewManager.setScreen(ViewManager.GAME_ID);
        viewManager.setController(this);
        viewManager.buildScreenGUI();
        setPlayers();
        (new Thread(this)).start();
    }
    public void setApplicationManager(ApplicationManager appManager){
        this.appManager = appManager;
    }
    synchronized public void sendMove(Vector2 position){
        if(game.makeMove(position)){
            viewManager.updateScreen();
        }
    }
    
    public Game getGame() {
        return game;
    }
    
    private void setPlayers(){
    	for(Pawn pawn : Pawn.values()){
            player[pawn.id()] = PlayerFactory.producePlayer(playerType[pawn.id()], pawn, this);
    	}
    }

	@Override
	public void run() {
		while(!game.getGameState().isTerminal()){
			System.out.println("witamy w petli glownej kurwo");
			try {
				//System.out.println("odpalamy");
				Thread playerThread = new Thread(player[game.getGameState().getPawn().id()]);
				playerThread.start();
				playerThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
