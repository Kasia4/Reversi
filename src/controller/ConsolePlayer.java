package controller;

import java.util.Scanner;

import model.Pawn;
import util.Vector2;

public class ConsolePlayer extends Player {

	public ConsolePlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("Podaj wspolrzedne ruchu");
		Scanner scanner = new Scanner(System.in);
		Vector2 pos = new Vector2();
		pos.x = scanner.nextInt() - 1;
		pos.y = scanner.nextInt() - 1;
		controllerHandle.sendMove(pos);
	}

}
