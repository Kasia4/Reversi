package controller;

import model.Pawn;
import util.Vector2;
import view.views.BoardView;

public class HumanPlayer extends Player {
	
	BoardView viewHandle;
	
	public HumanPlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("cos ty kurwo powiedzial?");
		viewHandle.resetMove();
		Vector2 currentMove;
		while((currentMove = viewHandle.getMove()) == null);
		controllerHandle.sendMove(currentMove);
	}
	
	@Override
	public void setController(GameController controller)
	{
		super.setController(controller);
		viewHandle = controller.viewManager.getScreen().findView(BoardView.class);
		if(viewHandle != null)
			System.out.println("gitara siema");
		
	}
	

}
