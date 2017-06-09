package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.screens.GameScreen;
import view.views.BoardView.RectDraw;

public class BoardMouseAdapter extends MouseAdapter{
    private GameScreen gameScreen;
    
    public BoardMouseAdapter(){
       // this.gameScreen = gameScreen;
    }
    public void mouseClicked(MouseEvent e){
        RectDraw clicked = (RectDraw) e.getSource();
        System.out.println(clicked.getPosition().x + " " + clicked.getPosition().y);
       // gameScreen.sendMove(clicked.getPosition());
    }
}
