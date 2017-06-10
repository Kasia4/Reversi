package model;

public enum Pawn {
WHITE(Field.WHITE, Field.BLACK, 0),
BLACK(Field.BLACK, Field.WHITE, 1);
	
	Field color;
	Field opposite;
	int id;
	
	private Pawn(Field color, Field opposite, int id){
		this.color = color;
		this.opposite = opposite;
		this.id = id;
	}
		
	public Field color(){
		return color;
	}
	public Field opposite(){
		return opposite;
	}
	public int id(){
		return id;
	}
}
