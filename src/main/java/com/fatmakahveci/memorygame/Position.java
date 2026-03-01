package com.fatmakahveci.memorygame;

/**
 * Immutable position on the game board.
 * Validation should be handled by Board.isCellValid(...),
 * so tests can create invalid positions (e.g., -1, -1).
 */
public record Position(int row, int col) {
	@Override
	public String toString() {
		return "(" + row + ", " + col + ")";
	}
}