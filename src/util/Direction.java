package util;

public enum Direction {
	N  (0, new Vector2( 0, -1)),
	NE (1, new Vector2( 1, -1)),
	E  (2, new Vector2( 1,  0)),
	SE (3, new Vector2( 1,  1)),
	S  (4, new Vector2( 0,  1)),
	SW (5, new Vector2(-1,  1)),
	W  (6, new Vector2(-1,  0)),
	NW (7, new Vector2(-1, -1));
	
	private int id;
	public Vector2 v;
	private Direction(int id, Vector2 v){
		this.v = v;
		this.id = id;
	}
	
	public int id(){ return id; }
	public Vector2 v(){ return v; }
}
