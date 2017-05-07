package util;

/**
 * Integer point on plane
 * @author Karol Checinski
 *
 */
public class Vector2{
	public int x;
	public int y;
	
	public Vector2(){;}
	
	public Vector2(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public Vector2(Vector2 w){
		this.x = w.x;
		this.y = w.y;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";
	}
	
	/**
	 * Add two vectors
	 * @param w
	 * @param v
	 * @return adding result
	 */
	static public Vector2 add(Vector2 w, Vector2 v){
		return new Vector2(w.x + v.x, w.y + v.y);
	}
	
	/**
	 * Subtracts two vectors
	 * @param w
	 * @param v
	 * @return subtraction result
	 */
	static public Vector2 sub(Vector2 w, Vector2 v){
		return new Vector2(w.x - v.x, w.y - v.y);
	}
	
	/**
	 * Multiples vector w by a constant c
	 * @param w
	 * @param c
	 * @return multiplication result
	 */
	static public Vector2 mul(Vector2 w, int c){
		return new Vector2(w.x * c, w.y * c);
	}
	
	static public Vector2 div(Vector2 w, int c){
		return new Vector2(w.x / c, w.y / c);
	}
	
	/** Basic vectors **/
	static public Vector2 Z()	{ 	return new Vector2( 0, 0);	}
	
	static public Vector2 W()   {	return new Vector2(-1, 0);	}
	static public Vector2 E()   {	return new Vector2( 1, 0);	}
	static public Vector2 N()   {	return new Vector2( 0,-1);	}
	static public Vector2 S()   {	return new Vector2( 0, 1);	}

	static public Vector2 WN()  { 	return new Vector2(-1,-1);	}
	static public Vector2 WS()  { 	return new Vector2(-1, 1);	}
	static public Vector2 EN()  {	return new Vector2( 1,-1);	}
	static public Vector2 ES()  { 	return new Vector2( 1, 1);	}
	
}