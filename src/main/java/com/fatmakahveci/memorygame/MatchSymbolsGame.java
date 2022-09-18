package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class MatchSymbolsGame {

	private Board gameBoard;
	private Player player1;
	private Player player2;
	private final int winScore;
	private Input in;
	private Player turn;

	public MatchSymbolsGame(Board gameBoard, Player player1, Player player2, int winScore, Input in) {
		this.gameBoard = gameBoard;
		this.player1 = player1;
		this.player2 = player2;
		this.winScore = winScore;
		this.in = in;
		this.turn = player1;
	}

	public Position userInput() {
		while (true) {
			Position pos = in.nextPositionInput();
			if (!gameBoard.isCellValid(pos)) {
				System.out.println("Cell position is not valid. Try again...\n");
			} else if (!gameBoard.isPlayable(pos)) {
				System.out.println("Cell is already open. Try another cell.\n");
			} else {
				return pos;
			}
		}
	}

	public void playOnce() {
		System.out.println(turn.getName() + " is playing...\n");
		Position pos1 = userInput();
		gameBoard.open(pos1);
		System.out.println(gameBoard);
		Position pos2 = userInput();
		gameBoard.open(pos2);
		System.out.println(gameBoard);
		if (gameBoard.play(pos1, pos2)) {
			turn.increaseScore();
		} else {
			turn = turn == player1 ? player2 : player1;
		}
	}

	public void play() {
		gameBoard.initBoard();
		System.out.println(gameBoard);
		while (!turn.hasWon(winScore)) {
			playOnce();
		}
		System.out.println(turn.getName() + " has won the game!");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of rows: ");
		final int rows = in.nextInt();
		System.out.print("Enter the number of cols: ");
		final int cols = in.nextInt();

		Board board = new Board(rows, cols);
		Player player1 = new Player("Player 1");
		Player player2 = new Player("Player 2");
		int winScore = (rows * cols) / 2;
		Input input = new ScannerInput(in);

		MatchSymbolsGame game = new MatchSymbolsGame(board, player1, player2, winScore, input);
		game.play();
	}
}
