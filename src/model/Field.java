package model;

public enum Field {
	EMPTY(true),
	WHITE(false),
	BLACK(false);
	
	boolean empty;
	
	private Field(boolean empty){
		this.empty = empty;
	}
	
	public boolean isEmpty(){
		return empty;
	}

}
