package model;

public enum BoardSize {
	SMALL(8),
	MEDIUM(16),
	LARGE(32);
	
	int size;
	
	private BoardSize(int size){
		this.size = size;
	}
	
	public int getValue(){
		return size;
	}
}
