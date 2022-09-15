package com.fatmakahveci.memorygame;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Board {

	private Cell[][] board;

	public Board(Cell[][] board) {
		checkBoardSize(board.length, board[0].length);
		this.board = board;
	}

	public Board(int rows, int cols) {
		checkBoardSize(rows, cols);
		this.board = new Cell[rows][cols];
	}

	public void checkBoardSize(int rows, int cols) {
		if ((rows <= 0) || (cols <= 0) || (rows % 2 == 1) || (cols % 2 == 1) || (rows * cols > 52)) {
			throw new IllegalArgumentException(
					"The size of the board is not valid. 0 < board size <= 52 and (rows and cols must be positive even integers)");
		}
	}

	public void initBoard() {
		List<Character> symbols = new ArrayList<>();
		for (int i = 0; i < (board.length * board[0].length) / 2; i++) {
			symbols.add((char) ('a' + i));
			symbols.add((char) ('a' + i));
		}
		Collections.shuffle(symbols);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				this.board[i][j] = new Cell(symbols.get(i * board[0].length + j));
			}
		}
	}

	public boolean isPlayable(Position pos) {
		return board[pos.getRow()][pos.getCol()].isPlayable();
	}

	public boolean isCellValid(Position pos) {
		if ((pos.getRow() < 0) || (pos.getRow() >= board.length) || (pos.getCol() < 0)
				|| (pos.getCol() >= board[0].length))
			return false;
		return true;
	}

	public void open(Position pos) {
		board[pos.getRow()][pos.getCol()].open();
	}

	public boolean play(Position pos1, Position pos2) {
		boolean match = board[pos1.getRow()][pos1.getCol()].isSameWith(board[pos2.getRow()][pos2.getCol()]);
		if (!match) {
			board[pos1.getRow()][pos1.getCol()].close();
			board[pos2.getRow()][pos2.getCol()].close();
		}
		return match;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("  ");
		for (int j = 0; j < board[0].length; j++) {
			sb.append(j + " ");
		}

		sb.append("\n");
		for (int i = 0; i < board.length; i++) {
			sb.append(i + " ");
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
