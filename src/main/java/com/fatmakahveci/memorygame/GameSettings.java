package com.fatmakahveci.memorygame;

/**
 * Holds validated game configuration.
 * Centralizes board size checks and win-score calculation.
 */
public record GameSettings(int rows, int cols) {

    public GameSettings {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and cols must be positive.");
        }

        int size = rows * cols;

        if (size % 2 != 0) {
            throw new IllegalArgumentException("Board size must be even (rows * cols must be divisible by 2).");
        }

        if (size > 52) {
            throw new IllegalArgumentException("Board size must be <= 52.");
        }
    }

    public int winScore() {
        return (rows * cols) / 2;
    }
}