package util;

public enum Direction {
	N  (new Vector2( 0, -1)),
	NE (new Vector2( 1, -1)),
	E  (new Vector2( 1,  0)),
	SE (new Vector2( 1,  1)),
	S  (new Vector2( 0,  1)),
	SW (new Vector2(-1,  1)),
	W  (new Vector2(-1,  0)),
	NW (new Vector2(-1,  1));
	
	public Vector2 v;
	private Direction(Vector2 v){
		this.v = v;
	}
}
