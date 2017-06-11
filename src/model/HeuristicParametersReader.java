package model;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import util.Vector2;


public class HeuristicParametersReader {
	
	static private final String SMALL_BOARD_FILENAME = "AI/small_parameters.txt";
	static private final String MEDIUM_BOARD_FILENAME = "AI/medium_parameters.txt";
	static private final String LARGE_BOARD_FILENAME = "AI/large_parameters.txt";
	
	private Map<Character, Float> weights = new HashMap<Character, Float>();
	
	private float[][] weightMatrix;
	
	public HeuristicParametersReader(BoardSize size)
	{
		try
		{
			loadParameters(size);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	void loadParameters(BoardSize size) throws IOException
	{
		String filename;
		weightMatrix = new float[size.getValue()][size.getValue()];
		switch(size)
		{
		case SMALL: filename = SMALL_BOARD_FILENAME; break;
		case MEDIUM:filename = MEDIUM_BOARD_FILENAME; break;
		case LARGE: filename = LARGE_BOARD_FILENAME; break;
		default : 	filename = "";
		}
		
		File file = null;
		Scanner scanner = null;
		
		
		try
		{
			file = new File(filename);
			scanner = new Scanner(file);
			for(FieldRegion region : FieldRegion.values()) 
			{
				if(!scanner.hasNextFloat())
					throw new IOException("Wrong file format");
				float a = scanner.nextFloat();
				weights.put(region.getSymbol(), a);
			}
			for(int y = 0; y < size.getValue(); ++y)
			{
				for(int x = 0; x < size.getValue(); ++x)
				{
					if(!scanner.hasNext())
						throw new IOException("Wrong file format");
					char c = scanner.next(".").charAt(0);
					Float value = weights.get(new Character(c));
					System.out.println(value);
					if(value == null)
						throw new IOException("Wrong file format");
					weightMatrix[x][y] = value;
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(scanner != null)scanner.close();
		}
	}
	
	public float getFieldWeight(Vector2 pos)
	{
		return weightMatrix[pos.x][pos.y];
	}
	
	public float[][] getWeightMatrix(){
	    return weightMatrix;
	}
	
	
	
}
