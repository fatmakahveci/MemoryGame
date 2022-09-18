package com.fatmakahveci.memorygame;

import java.util.Scanner;

public class MatchSymbolsGame {

	private Board board;
	private Player player1;
	private Player player2;
	private final int winScore;
	private Input in;
	private Player turn;

	public MatchSymbolsGame(Board board, Player player1, Player player2, int winScore, Input in) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
		this.winScore = winScore;
		this.in = in;
		this.turn = player1;
	}

	public Position userInput() {
		while (true) {
			Position position = in.nextPositionInput();
			if (!board.isCellValid(position)) {
				System.out.println("Cell position is not valid. Try again...\n");
			} else if (!board.isPlayable(position)) {
				System.out.println("Cell is already open. Try another cell.\n");
			} else {
				return position;
			}
		}
	}

	public void playOnce() {
		System.out.println(turn.getName() + " is playing...\n");
		Position position1 = userInput();
		board.open(position1);
		System.out.println(board);
		Position position2 = userInput();
		board.open(position2);
		System.out.println(board);
		if (board.play(position1, position2)) {
			turn.increaseScore();
		} else {
			turn = turn == player1 ? player2 : player1;
		}
	}

	public Player whoseTurn() {
		return turn;
	}

	public void play() {
		board.initBoard();
		System.out.println(board);
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
