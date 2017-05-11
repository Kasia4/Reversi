package model;

public enum GameState {
	TURN_A(false),
	TURN_B(false),
	WIN_A(true),
	WIN_B(true);
	
	private boolean terminal;

	private GameState(boolean terminal){
		this.terminal = terminal;
	}
	
	public boolean isTerminal(){
		return terminal;
	}
}
