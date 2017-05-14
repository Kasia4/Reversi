package view.screens;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;

import controller.AbstractController;

import java.util.ArrayList;

import view.views.*;

/**
 * Basic abstract screen class. Represents one screen in application
 * @author Karol Checinski
 *
 */
abstract public class Screen extends JPanel {

	private static final long serialVersionUID = 5999402931330232852L;

	static final Dimension SCREEN_SIZE = new Dimension(640,480);
	
	/**
	 * Reference to active controller
	 */
	protected AbstractController controller;
	/**
	 * List of dependent views
	 */
	private ArrayList<AbstractView> views;
	
	public Screen(AbstractController controller){
		views = new ArrayList<AbstractView>();
		setController(controller);
		
		setSize(SCREEN_SIZE);
	}
	
	/**
	 * Sets current controller
	 * @param controller
	 */
	public void setController(AbstractController controller){
		this.controller = controller;
		for(AbstractView view: views){
			try{
				view.validateAndSetController(controller);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void addView(AbstractView view){
		views.add(view);
		try{
			view.validateAndSetController(controller);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	public void removeView(AbstractView view){
		view.setVisible(false);
		views.remove(view);
	}
	
	abstract public void buildGUI();
	
}
