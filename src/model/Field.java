package model;

public enum Field {
	EMPTY(true, "E"),
	WHITE(false, "W"),
	BLACK(false, "B");
	
	boolean empty;
	String symbol;
	
	private Field(boolean empty, String symbol){
		this.empty = empty;
		this.symbol = symbol;
	}
	
	public boolean isEmpty(){
		return empty;
	}
	
	public String toString(){
		return symbol;
	}
}
