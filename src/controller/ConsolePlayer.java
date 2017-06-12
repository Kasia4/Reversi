package controller;

import java.util.Scanner;

import model.Pawn;
import util.Vector2;

import java.util.InputMismatchException;

public class ConsolePlayer extends Player {

	private Scanner scanner;

	public ConsolePlayer(Pawn pawn, GameController controller) {
		super(pawn, controller);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println(pawn.toString() + ": Podaj wspolrzedne ruchu \"x y\"");
		scanner = new Scanner(System.in);
		Vector2 pos = new Vector2();
		boolean success = false;
		while(!success){
			try{
				pos.x = scanner.nextInt() - 1;
				pos.y = scanner.nextInt() - 1;
				success = true;
			}
			catch(InputMismatchException e){
				System.out.println("Bledny format ruchu, sproboj ponownie");
				scanner.nextLine();
			}
		}
		controllerHandle.sendMove(pos);
	}

}
