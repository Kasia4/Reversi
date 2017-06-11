package controller;

import java.io.BufferedReader;
import java.io.PrintWriter;

import model.BoardSize;
import model.Game;
import model.Pawn;
import util.Vector2;
import view.ViewManager;


public class GameController extends AbstractController implements Runnable{

    ApplicationManager appManager;
    Game game;
    Connection connection;
    PlayerType playerType[] = new PlayerType[2];
    AbstractPlayer player[] = new Player[2];
    
    String hostname = "localhost";
    int port = 4543;


    public GameController(ViewManager viewManager, BoardSize boardSize, PlayerType whiteType, PlayerType blackType) {
        super(viewManager);     
        game = new Game(boardSize);
        playerType[Pawn.WHITE.id()] = whiteType;
        playerType[Pawn.BLACK.id()] = blackType;
        if(whiteType == PlayerType.REMOTE || blackType == PlayerType.REMOTE)
            connection = new Connection(hostname, port);
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
            System.out.println("PO WYJSCIU KURWA Z RUCHU " + game.getGameState());
        }
    }
    
    public Game getGame() {
        return game;
    }
    
    private void setPlayers(){
    	for(Pawn pawn : Pawn.values()){
            player[pawn.id()] = PlayerFactory.producePlayer(playerType[pawn.id()], pawn, this, false);
    	}
    }

	@Override
	public void run() {
		while(!game.getGameState().isTerminal()){
			try {
				Thread playerThread = new Thread(player[game.getGameState().getPawn().id()]);
				playerThread.start();
				playerThread.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public PrintWriter getPrintWriter(){
	    return connection.getPrintWriter();
	}
	
    public BufferedReader getBufferedReader(){
        return connection.getBufferedReader();
    }
}
