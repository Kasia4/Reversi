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
	
	public Vector2 getSize(){
		return new Vector2(size,size);
	}
}
