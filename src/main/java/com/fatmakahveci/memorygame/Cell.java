package com.fatmakahveci.memorygame;

import java.util.Objects;

public class Cell {

	private boolean open;
	private final char symbol;

	public Cell(char symbol) {
		this.symbol = symbol;
		this.open = false;
	}

	/** Returns true if the cell is currently face-up. */
	public boolean isOpen() {
		return open;
	}

	/** A cell is playable only when it is still face-down. */
	public boolean isPlayable() {
		return !open;
	}

	/** Reveal the cell. */
	public void open() {
		this.open = true;
	}

	/** Hide the cell again. */
	public void close() {
		this.open = false;
	}

	public char getSymbol() {
		return symbol;
	}

	/** Returns true if this cell has the same symbol as the other cell. */
	public boolean isSameWith(Cell other) {
		Objects.requireNonNull(other, "other must not be null");
		return this.symbol == other.symbol;
	}

	@Override
	public String toString() {
		return open ? String.valueOf(symbol) : "*";
	}
}