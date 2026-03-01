package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class MemoryGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the number of rows: ");
		final int rows = scanner.nextInt();

		System.out.print("Enter the number of cols: ");
		final int cols = scanner.nextInt();

		Board board = new Board(rows, cols);
		int winScore = (rows * cols) / 2;

		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");

		Input input = new ScannerInput(scanner);

		GameEngine engine = new GameEngine(board, player1, player2, winScore);
		ConsoleUI ui = new ConsoleUI(engine, input);

		ui.run();
	}
}