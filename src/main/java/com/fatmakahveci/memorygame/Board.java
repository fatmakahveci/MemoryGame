package com.fatmakahveci.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Board {

	private static final List<Character> SYMBOL_POOL = buildSymbolPool();

	private final Cell[][] board;

	public Board(Cell[][] board) {
		Objects.requireNonNull(board, "board must not be null");
		if (board.length == 0 || board[0].length == 0) {
			throw new IllegalArgumentException("Board cannot be empty.");
		}

		checkBoardSize(board.length, board[0].length);
		this.board = board;

		// Validate rectangular board + non-null cells
		int rows = board.length;
		int cols = board[0].length;

		for (int r = 0; r < rows; r++) {
			if (board[r] == null || board[r].length != cols) {
				throw new IllegalArgumentException("Board must be rectangular (row " + r + ").");
			}
			for (int c = 0; c < cols; c++) {
				if (board[r][c] == null) {
					throw new IllegalArgumentException("Empty cell (row:" + r + ",col:" + c + ")");
				}
			}
		}
	}

	public Board(int rows, int cols) {
		checkBoardSize(rows, cols);
		this.board = new Cell[rows][cols];
	}

	public void checkBoardSize(int rows, int cols) {
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Rows and cols must be positive integers.");
		}

		int size = rows * cols;
		if (size % 2 == 1) {
			throw new IllegalArgumentException("Board size must be even (rows * cols must be divisible by 2).");
		}

		if (size > 52) {
			throw new IllegalArgumentException("Board size must be <= 52.");
		}

		int pairs = size / 2;
		if (pairs > SYMBOL_POOL.size()) {
			throw new IllegalArgumentException("Board too large for available symbols.");
		}
	}

	/**
	 * Initializes the board with randomly shuffled pairs.
	 */
	public void initBoard() {
		initBoard(new Random());
	}

	/**
	 * Initializes the board with a provided Random (useful for deterministic
	 * tests).
	 */
	public void initBoard(Random random) {
		Objects.requireNonNull(random, "random must not be null");

		int rows = board.length;
		int cols = board[0].length;
		int size = rows * cols;
		int pairs = size / 2;

		List<Character> symbols = new ArrayList<>(size);
		for (int i = 0; i < pairs; i++) {
			char s = SYMBOL_POOL.get(i);
			symbols.add(s);
			symbols.add(s);
		}

		Collections.shuffle(symbols, random);

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				char symbol = symbols.get(r * cols + c);
				board[r][c] = new Cell(symbol);
			}
		}
	}

	public boolean isPlayable(Position pos) {
		return board[pos.getRow()][pos.getCol()].isPlayable();
	}

	public boolean isCellValid(Position pos) {
		int r = pos.getRow();
		int c = pos.getCol();
		return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
	}

	public void open(Position pos) {
		board[pos.getRow()][pos.getCol()].open();
	}

	public boolean play(Position pos1, Position pos2) {
		boolean match = board[pos1.getRow()][pos1.getCol()]
				.isSameWith(board[pos2.getRow()][pos2.getCol()]);

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
		for (int c = 0; c < board[0].length; c++) {
			sb.append(c).append(' ');
		}
		sb.append('\n');

		for (int r = 0; r < board.length; r++) {
			sb.append(r).append(' ');
			for (int c = 0; c < board[0].length; c++) {
				sb.append(board[r][c]).append(' ');
			}
			sb.append('\n');
		}

		return sb.toString();
	}

	private static List<Character> buildSymbolPool() {
		List<Character> pool = new ArrayList<>(52);
		for (char c = 'A'; c <= 'Z'; c++)
			pool.add(c);
		for (char c = 'a'; c <= 'z'; c++)
			pool.add(c);
		return Collections.unmodifiableList(pool);
	}
}