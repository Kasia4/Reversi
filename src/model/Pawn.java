package model;

public enum Pawn {
WHITE(Field.WHITE, Field.BLACK),
BLACK(Field.BLACK, Field.WHITE);
	
	Field color;
	Field opposite;
	private Pawn(Field color, Field opposite){
		this.color = color;
		this.opposite = opposite;
	}
		
	public Field color(){
		return color;
	}
	public Field opposite(){
		return opposite;
	}
}
