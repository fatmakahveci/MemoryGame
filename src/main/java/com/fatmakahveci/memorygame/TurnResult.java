package com.fatmakahveci.memorygame;

/**
 * Result of a move.
 */
public record TurnResult(
        boolean match,
        Player nextTurn,
        int player1Score,
        int player2Score,
        boolean gameOver) {
}