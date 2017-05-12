package model;

public enum GameState {
	TURN_B(false),
	TURN_W(false),
	WIN_B(true),
	WIN_W(true);
	
	private boolean terminal;

	private GameState(boolean terminal){
		this.terminal = terminal;
	}
	
	public boolean isTerminal(){
		return terminal;
	}
}
