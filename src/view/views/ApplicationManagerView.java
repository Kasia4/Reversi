package view.views;

import java.awt.LayoutManager;

import controller.AbstractController;
import controller.ApplicationManager;

/**
 * View compatible with application manager
 * @author Karol Checinski
 *
 */
abstract public class ApplicationManagerView extends AbstractView {
	
	private static final long serialVersionUID = -150930803484368543L;
	@Override
	protected String giveRequiredControllerType(){
		return "class controller.ApplicationManager";
	}
	
	
	protected ApplicationManager appManager;
	
	public ApplicationManagerView() {
	}

	public ApplicationManagerView(LayoutManager arg0) {
		super(arg0);
	}

	public ApplicationManagerView(boolean arg0) {
		super(arg0);
	}

	public ApplicationManagerView(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
	}

	@Override
	protected void setController(AbstractController controller) {
		appManager = (ApplicationManager)controller;
	}
}
