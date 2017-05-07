package util;

import java.util.Vector;

public class Matrix <T>{
	private Vector<Vector<T>> matrix;
	private Vector2 size;


	public Matrix(Vector2 size){
		matrix = new Vector<Vector<T>>();
		setSize(size);
	}

	//Sets size of matrix
	public void setSize(Vector2 size){
		matrix.clear();
		this.size = size;
		for(int i = 0; i <size.y ; i++){
			matrix.add(new Vector<T>());
			for(int j = 0; j < size.x; j++){
				matrix.get(i).add(null);
			}
		}
	}
	
	//Sets object on x,y
	public void setField(Vector2 pos, T object){
		matrix.get(pos.y).set(pos.x, object);
	}
	
	//Gets object from (x,y)
	public T getField(Vector2 pos){
		return matrix.get(pos.y).get(pos.x);
	}
	
	//fills matrix with object
	public void fill(T object){
		for(int y = 0; y < size.y; y++){
			for(int x = 0; x < size.x; x++){
				setField(new Vector2(x,y), object);
			}
		}
	}
	
	//print out matrix to console
	public void printOut(){
		for(int y = 0; y < size.y; y++){
			for(int x = 0; x < size.x; x++){
				System.out.print(getField(new Vector2(x,y)) + " ");
			}
		System.out.println("");
		}
	}
	
	public boolean isValid(Vector2 pos){
		return pos.x >= 0 && pos.x < size.x && pos.y >= 0 && pos.y < size.y;
	}
		
}

