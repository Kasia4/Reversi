package controller;

import model.Game;

/**
 * Abstract message used to communicate between Controller and game Model
 * @author Karol Checinski
 *
 */
public interface AbstractControllerMessage {
	/**
	 * Execute task given for model
	 * @param model
	 */
	public void process(Game model);
}
