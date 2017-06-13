package model;

public enum FieldRegion {
	CORNER('A'),
	CORNER_EDGE('B'),
	CORNER_NEAR('C'),
	EDGE('D'),
	EDGE_NEAR('E'),
	CENTER('F');
	
	char symbol;
	
	FieldRegion(char symbol){
		this.symbol = symbol;
	}
	
	public char getSymbol()
	{
		return symbol;
	}
	
}
