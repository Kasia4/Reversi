package view.views;

import java.awt.LayoutManager;
import javax.swing.JPanel;

import controller.AbstractController;

import java.io.IOException;

/**
 * Basic abstract view class.
 * @author Karol Checinski
 *
 */
abstract public class AbstractView extends JPanel {

	private static final long serialVersionUID = 6154337802221482019L;
	/**
	 * Information about controller which is compatible with view
	 */
	private String requiredControllerType;
	
	public AbstractView() {
		requiredControllerType = giveRequiredControllerType();
	}

	public AbstractView(LayoutManager arg0) {
		super(arg0);
		requiredControllerType = giveRequiredControllerType();
	}

	public AbstractView(boolean arg0) {
		super(arg0);
		requiredControllerType = giveRequiredControllerType();
	}

	public AbstractView(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		requiredControllerType = giveRequiredControllerType();
	}
	
	/**
	 * Check if current controller is compatible
	 * @param controller
	 * @throws IOException when controller is wrong
	 */
	public void validateAndSetController(AbstractController controller) throws IOException{
		String controllerType = "" + controller.getClass();
		if(!controllerType.equals(requiredControllerType)){
			System.out.println(this.getClass());
			System.out.println(requiredControllerType);
			System.out.println(controllerType);
			throw new IOException("Wrong controller type");
		}
		else setController(controller); 
	}
	abstract protected void setController(AbstractController controller);
	abstract public void buildGUI();
	
	/**
	 * Sets properly controller type
	 */
	abstract protected String giveRequiredControllerType();	
	protected String getRequiredControllerType(){
		return requiredControllerType;
	}
	
}
