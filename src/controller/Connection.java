package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Connection {
    private String hostname;
    private int port;
    private Socket socket;
    private int numberOfGame;
    
    public Connection(String hostname, int port){
        this.hostname = hostname;
        this.port = port;
        try {
            socket = new Socket(hostname, port);
            BufferedReader in = getBufferedReader();
            numberOfGame = Integer.parseInt(in.readLine()); 
            System.out.println(numberOfGame);
            
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
}
