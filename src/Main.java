
import model.Matrix;
public class Main {	
	public static void main(String[] args){
		Matrix<Integer> temp = new Matrix<Integer>(8,8);
		temp.fill(10);
		System.out.println(temp.getField(5,5));
	}
}
