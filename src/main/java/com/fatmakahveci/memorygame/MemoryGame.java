package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class MemoryGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		GameSettings settings = readSettings(scanner);

		Board board = new Board(settings.rows(), settings.cols());
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");

		Input input = new ScannerInput(scanner);

		GameEngine engine = new GameEngine(board, player1, player2, settings.winScore());
		ConsoleUI ui = new ConsoleUI(engine, input);

		ui.run();
	}

	private static GameSettings readSettings(Scanner scanner) {
		while (true) {
			try {
				System.out.print("Enter the number of rows: ");
				int rows = scanner.nextInt();

				System.out.print("Enter the number of cols: ");
				int cols = scanner.nextInt();

				return new GameSettings(rows, cols);
			} catch (Exception e) {
				// If the user typed non-integer, clear scanner buffer
				scanner.nextLine();
				System.out.println("\nInvalid board size. Please try again.");
				System.out.println("Rules: rows*cols must be even, and total cells must be <= 52.\n");
			}
		}
	}
}