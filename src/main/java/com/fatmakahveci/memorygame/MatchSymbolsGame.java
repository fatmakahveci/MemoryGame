package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class MatchSymbolsGame {
	private Board gameBoard;
	private Player player1;
	private Player player2;
	private final int winScore;
	private Scanner in;

	public MatchSymbolsGame(int rows, int cols, Scanner in) {
		this.gameBoard = new Board(rows, cols);
		this.player1 = new Player("Player 1");
		this.player2 = new Player("Player 2");
		this.winScore = (rows * cols) / 2 - 1;
		this.in = in;
	}

	public Position userInput() {
		while (true) {
			System.out.print("Enter cell row: ");
			int row = in.nextInt();
			System.out.print("Enter cell col: ");
			int col = in.nextInt();
			System.out.println();
			Position pos = new Position(row, col);
			if (!gameBoard.isCellValid(pos)) {
				System.out.println("Cell position is not valid. Try again...\n");
			} else if (!gameBoard.isPlayable(pos)) {
				System.out.println("Cell is already open. Try another cell.\n");
			} else {
				return pos;
			}
		}
	}

	public Player move(Player player) {
		System.out.println(player.getName() + " is playing...\n");
		Position pos1 = userInput();
		gameBoard.open(pos1);
		System.out.println(gameBoard);
		Position pos2 = userInput();
		gameBoard.open(pos2);
		System.out.println(gameBoard);
		if (gameBoard.play(pos1, pos2)) {
			player.increaseScore();
			return player;
		} else {
			return player == player1 ? player2 : player1;
		}
	}

	public void play() {
		gameBoard.initBoard();
		System.out.println(gameBoard);
		Player turn = player1;
		while (true) {
			turn = move(turn);
			if (turn.hasWon(winScore)) {
				System.out.println(turn.getName() + " has won the game!");
				return;
			}
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of rows: ");
		final int rows = in.nextInt();
		System.out.print("Enter the number of cols: ");
		final int cols = in.nextInt();

		MatchSymbolsGame game = new MatchSymbolsGame(rows, cols, in);
		game.play();
	}
}
