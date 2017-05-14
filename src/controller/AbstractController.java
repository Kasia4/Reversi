package controller;

import view.ViewManager;
/**
 * Base controller class. Has reference to viewManager.
 * @author Karol Checinski
 *
 */
public abstract class AbstractController {
	
	protected ViewManager viewManager;
	
	/**
	 * Class constructor specifying ViewManager
	 * @param viewManager
	 */
	public AbstractController(ViewManager viewManager) {
		this.viewManager = viewManager;
	}
	
	/**
	 * Prepare controller to use;
	 */
	abstract public void launch();
	
	

}
