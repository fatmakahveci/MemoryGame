package com.fatmakahveci.memorygame;

public class Player {

	private final String name;
	private int score;

	public Player(String name) {
		this(name, 0);
	}

	public Player(String name, int score) {
		this.name = name;
		if (score < 0) {
			throw new IllegalArgumentException("Score cannot be negative.");
		}
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void increaseScore() {
		score++;
	}

	public boolean hasWon(int winningScore) {
		return score >= winningScore;
	}
}
