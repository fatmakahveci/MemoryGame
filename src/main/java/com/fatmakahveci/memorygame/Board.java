package com.fatmakahveci.memorygame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Board {

	private static final List<Character> SYMBOL_POOL = buildSymbolPool();

	private final Cell[][] board;
	private boolean initialized;

	public int rows() {
		return board.length;
	}

	public int cols() {
		return board[0].length;
	}

	/**
	 * Use this constructor for tests (or advanced use-cases) where you want a
	 * preset board.
	 * The board is considered initialized and initBoard() becomes a no-op.
	 */
	public Board(Cell[][] board) {
		Objects.requireNonNull(board, "board must not be null");
		if (board.length == 0 || board[0] == null || board[0].length == 0) {
			throw new IllegalArgumentException("Board cannot be empty.");
		}

		int rows = board.length;
		int cols = board[0].length;

		checkBoardSize(rows, cols);

		// Validate rectangular board + non-null cells
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

		this.board = board;
		this.initialized = true;
	}

	public Board(int rows, int cols) {
		checkBoardSize(rows, cols);
		this.board = new Cell[rows][cols];
		this.initialized = false;
	}

	public static void checkBoardSize(int rows, int cols) {
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

	public boolean isInitialized() {
		return initialized;
	}

	/**
	 * Initializes the board with randomly shuffled pairs (if not already
	 * initialized).
	 */
	public void initBoard() {
		initBoard(new Random());
	}

	/**
	 * Initializes the board with a provided Random (useful for deterministic
	 * tests),
	 * but only if the board is not already initialized.
	 */
	public void initBoard(Random random) {
		Objects.requireNonNull(random, "random must not be null");

		if (initialized) {
			return; // important: do not override preset boards (tests)
		}

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

		initialized = true;
	}

	public boolean isPlayable(Position pos) {
		return board[pos.row()][pos.col()].isPlayable();
	}

	public boolean isCellValid(Position pos) {
		int r = pos.row();
		int c = pos.col();
		return r >= 0 && r < board.length && c >= 0 && c < board[0].length;
	}

	public void open(Position pos) {
		board[pos.row()][pos.col()].open();
	}

	public boolean play(Position pos1, Position pos2) {
		boolean match = board[pos1.row()][pos1.col()]
				.isSameWith(board[pos2.row()][pos2.col()]);

		if (!match) {
			board[pos1.row()][pos1.col()].close();
			board[pos2.row()][pos2.col()].close();
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