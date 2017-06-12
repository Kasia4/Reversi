package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import util.Vector2;


public class Connection {

    private Socket socket;
    private int numberOfGame;
    
    public Connection(String hostname, int port){
        try {
            socket = new Socket(hostname, port);

            BufferedReader in = getBufferedReader();
            numberOfGame = Integer.parseInt(in.readLine()); 
               
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public PrintWriter getPrintWriter(){
        try {
            return new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public BufferedReader getBufferedReader(){
        try {
            return new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String posToString(Vector2 vec){
    	return "" + vec.x + " " + vec.y;
    }
    
    public static Vector2 stringToPos(String str){
    	Scanner scanner = new Scanner(str);
    	Vector2 res = new Vector2();
    	res.x = scanner.nextInt();
    	res.y = scanner.nextInt();
    	scanner.close();
    	return res;
    }
    public int getNumberOfGame(){
        return numberOfGame;
    }
}
