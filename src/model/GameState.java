package model;

public enum GameState {
	TURN_B(false, Pawn.BLACK, Pawn.WHITE),
	TURN_W(false, Pawn.WHITE, Pawn.BLACK),
	WIN_B(true, Pawn.BLACK, Pawn.WHITE),
	WIN_W(true, Pawn.WHITE, Pawn.BLACK),
	DRAW(true, Pawn.WHITE, Pawn.BLACK);
	
	private boolean terminal;
	private Pawn pawn;
	private Pawn oposite;

	private GameState(boolean terminal, Pawn pawn, Pawn oposite){
		this.terminal = terminal;
		this.pawn = pawn;
		this.oposite = oposite;
	}
	
	public boolean isTerminal(){
		return terminal;
	}
	public Pawn getPawn(){
	    return pawn;
	}
	public Pawn getOpositePawn(){
	    return oposite;
	}
}
