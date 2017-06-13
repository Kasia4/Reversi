package controller;

import java.io.BufferedReader;
import java.io.PrintWriter;

import model.BoardSize;
import model.Game;
import model.Move;
import model.Pawn;
import util.Vector2;
import view.ViewManager;
import view.views.ControlPanelView;


public class GameController extends AbstractController implements Runnable{

    ApplicationManager appManager;
    Game game;
    Connection connection;
    PlayerType playerType[] = new PlayerType[2];
    AbstractPlayer player[] = new AbstractPlayer[2];
    int numberOfGame;
    boolean remoteGame = false;
    String hostname = "localhost";
    int port = 4543;


    public GameController(ViewManager viewManager, BoardSize boardSize, PlayerType player1, PlayerType player2) {
        super(viewManager);     
        game = new Game(boardSize);
        playerType[Pawn.WHITE.id()] = player1;
        playerType[Pawn.BLACK.id()] = player2;
    }

    @Override
    public void launch() {
        viewManager.setScreen(ViewManager.GAME_ID);
        viewManager.setController(this);
        viewManager.buildScreenGUI();
        setConnection();
        setPlayers();
        (new Thread(this)).start();

    }
    private void setConnection(){
        if(playerType[0] == PlayerType.REMOTE || playerType[1] == PlayerType.REMOTE){
            connection = new Connection(hostname, port);
            remoteGame = true;
            if( (numberOfGame = connection.getNumberOfGame()) == 2){
                PlayerType toChange = playerType[Pawn.WHITE.id()];
                playerType[Pawn.WHITE.id()] = playerType[Pawn.BLACK.id()];
                playerType[Pawn.BLACK.id()] = toChange;

            }
            else{
                ControlPanelView tmp = viewManager.getScreen().findView(ControlPanelView.class);
                tmp.changePawnColor();
            }
                
        }
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
        if(remoteGame){
            boolean sender = true;
            if(numberOfGame == 2)
                sender = false;
            AbstractPlayer tmp = PlayerFactory.producePlayer(playerType[0], Pawn.WHITE, this, sender);
           // System.out.println(tmp.getClass());
            player[0] = tmp;
            player[1] = PlayerFactory.producePlayer(playerType[1], Pawn.BLACK, this, !sender);
        }
        else
        	for(Pawn pawn : Pawn.values()){
                player[pawn.id()] = PlayerFactory.producePlayer(playerType[pawn.id()], pawn, this, false);
        	}
    }

	@Override
	public void run() {
		while(!game.getGameState().isTerminal()){
			if(game.getEmptyMoveRequired()) {
				sendMove(Move.emptyMoveVector());
				continue;
			}
			try {
				Thread playerThread = new Thread(player[game.getGameState().getPawn().id()]);
				playerThread.start();
				playerThread.join();
			} catch (InterruptedException e) {
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
