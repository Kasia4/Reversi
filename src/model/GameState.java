package model;

public enum GameState {
	TURN_B(false, Pawn.BLACK, Pawn.WHITE),
	TURN_W(false, Pawn.WHITE, Pawn.BLACK),
	WIN_B(true, Pawn.BLACK, Pawn.WHITE),
	WIN_W(true, Pawn.WHITE, Pawn.BLACK),
	DRAW(true, Pawn.WHITE, Pawn.BLACK);
	
	private boolean terminal;
	private Pawn pawn;
	private Pawn opposedPawn;

	private GameState(boolean terminal, Pawn pawn, Pawn opposedPawn){
		this.terminal = terminal;
		this.pawn = pawn;
		this.opposedPawn = opposedPawn;
	}
	
	public boolean isTerminal(){
		return terminal;
	}
	public Pawn getPawn(){
	    return pawn;
	}
	public Pawn getOpposedPawn(){
	    return opposedPawn;
	}
}
