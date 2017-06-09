package view.views;

import java.awt.LayoutManager;

import controller.AbstractController;
import controller.GameController;

/**
 * View compatible with application manager
 * @author Karol Checinski
 *
 */
abstract public class GameManagerView extends AbstractView {
    
    private static final long serialVersionUID = -150930803484368543L;
    @Override
    protected String giveRequiredControllerType(){
        return "class controller.GameController";
    }
    
    
    protected GameController gameController;
    
    public GameManagerView() {
    }

    public GameManagerView(LayoutManager arg0) {
        super(arg0);
    }

    public GameManagerView(boolean arg0) {
        super(arg0);
    }

    public GameManagerView(LayoutManager arg0, boolean arg1) {
        super(arg0, arg1);
    }

    @Override
    protected void setController(AbstractController controller) {
        gameController = (GameController)controller;
    }
}
