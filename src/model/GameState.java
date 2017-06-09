package model;

public enum GameState {
	TURN_B(false, Pawn.BLACK, Pawn.WHITE),
	TURN_W(false, Pawn.WHITE, Pawn.BLACK),
	WIN_B(true, Pawn.BLACK, Pawn.WHITE),
	WIN_W(true, Pawn.WHITE, Pawn.BLACK);
	
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
	    if(pawn == Pawn.BLACK)
	        return Pawn.BLACK;
	    else
	        return Pawn.WHITE;
	}
	public Pawn getOpositePawn(){
	       if(pawn == Pawn.WHITE)
	            return Pawn.BLACK;
	        else
	            return Pawn.WHITE;
	}
}
