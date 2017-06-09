package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import view.views.BoardView;
import view.views.BoardView.RectDraw;

public class BoardMouseAdapter extends MouseAdapter{
    private BoardView boardView;
    
    public BoardMouseAdapter(BoardView boardView){
        this.boardView = boardView;
    }
    public void mousePressed(MouseEvent e){
        RectDraw clicked = (RectDraw) e.getSource();
        boardView.sendMove(clicked.getPosition());
    }
}
