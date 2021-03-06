package view.views;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import controller.PlayerType;
import model.BoardSize;

import java.awt.event.*;

/**
 * Main menu view.
 * @author Karol Checinski
 *
 */

public class MenuView extends ApplicationManagerView {
	
	private static final long serialVersionUID = -955436571606424870L;
	static final Dimension BTN_SIZE = new Dimension (200, 40);
	static final String HUMAN_GAME = "Human vs. Human";
	static final String HUMAN_AI_GAME = "Human vs. AI";
	static final String AI_GAME = "AI vs. Remote";
	static final String EXIT_GAME = "Exit";
	
	JLabel title;
	JComboBox<String> boardSize;
	JButton humanGameButton;
	JButton humanAiGameButton;
	JButton aiGameButton;
	JButton exitButton;
	
	public MenuView() {
	}

	public MenuView(LayoutManager arg0) {
		super(arg0);
	}

	public MenuView(boolean arg0) {
		super(arg0);
	}
	public MenuView(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
	}
	
	public void buildGUI(){
		title = new JLabel("REVERSI", JLabel.CENTER);
		title.setMaximumSize(BTN_SIZE);
		
		String[] sizes = {"8x8", "16x16", "32x32"};
		boardSize = new JComboBox<String>(sizes);
		
		
		humanGameButton = new JButton(HUMAN_GAME);
		humanGameButton.setPreferredSize(BTN_SIZE);
		humanGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				humanGameButtonActionPerformed(evt);
			}
		});
		
		humanAiGameButton = new JButton(HUMAN_AI_GAME);
		humanAiGameButton.setPreferredSize(BTN_SIZE);
		humanAiGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				humanAiGameButtonActionPerformed(evt);
			}
		});
		
		aiGameButton = new JButton(AI_GAME);
		aiGameButton.setPreferredSize(BTN_SIZE);
		aiGameButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				aiGameButtonActionPerformed(evt);
			}
		});
		
		exitButton = new JButton(EXIT_GAME);
		exitButton.setPreferredSize(BTN_SIZE);
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				exitButtonActionPerformed(evt);
			}
		});
	
		add(title);
		add(boardSize);
		add(humanGameButton);
		add(humanAiGameButton);
		add(aiGameButton);
		add(exitButton);
		
		setLayout(new GridLayout(0,1));
		
	}
	
	private void humanGameButtonActionPerformed(ActionEvent evt){
	    appManager.createGame(getSize(boardSize.getSelectedIndex()), PlayerType.HUMAN, PlayerType.HUMAN);
	}
	private void humanAiGameButtonActionPerformed(ActionEvent evt){
		appManager.createGame(getSize(boardSize.getSelectedIndex()), PlayerType.HUMAN, PlayerType.AI);
	}
	private void aiGameButtonActionPerformed(ActionEvent evt){
	    appManager.createGame(getSize(boardSize.getSelectedIndex()), PlayerType.AI, PlayerType.REMOTE);
	}
	private void exitButtonActionPerformed(ActionEvent evt){
		appManager.exit();
	}
	private BoardSize getSize(int i){
	    switch(i){
	    case 0: return BoardSize.SMALL;
	    case 1: return BoardSize.MEDIUM;
	    case 2: return BoardSize.LARGE;
	    default: return BoardSize.SMALL;
	    }
	}
}
