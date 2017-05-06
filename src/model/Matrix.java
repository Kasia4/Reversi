package model;

import java.util.Vector;

public class Matrix <T>{
	private Vector<Vector<T>> matrix;
	private int width, heigth;


	public Matrix(int x, int y){
		matrix = new Vector<Vector<T>>();
		setSize(x, y);
		this.width = x;
		this.heigth = y;
	}

	//Sets size of matrix
	public void setSize(int x, int y){
		matrix.clear();
		this.width = x;
		this.heigth = y;
		for(int i = 0; i < x; i++){
			matrix.add(new Vector<T>());
			for(int j = 0; j < y; j++){
				matrix.get(i).add(null);
			}
		}
	}
	
	//Sets object on x,y
	public void setField(int x, int y, T object){
		matrix.get(x).set(y, object);
	}
	
	//Gets object from (x,y)
	public T getField(int x, int y){
		return matrix.get(x).get(y);
	}
	
	//fills matrix with object
	public void fill(T object){
		for(int i = 0; i < width; i++){
			for(int j = 0; j < heigth; j++){
				setField(i,j, object);
			}
		}
	}
	
	
}

