
import model.Matrix;
public class Main {	
	public static void main(String[] args){
		Matrix<Integer> temp = new Matrix<Integer>(8,8);
		temp.fill(10);
		temp.setField(0, 0, 99);
		temp.printOut();
	}
}
