package com.fatmakahveci.memorygame;

import java.util.Objects;

/**
 * Pure game rules/state holder.
 * No printing, no reading input.
 */
public class GameEngine {

    private final Board board;
    private final Player player1;
    private final Player player2;
    private final int winScore;

    private Player turn;

    // Stats
    private int moves; // each move = selecting 2 cells
    private int matches; // successful matches

    public GameEngine(Board board, Player player1, Player player2, int winScore) {
        this.board = Objects.requireNonNull(board);
        this.player1 = Objects.requireNonNull(player1);
        this.player2 = Objects.requireNonNull(player2);
        this.winScore = winScore;
        this.turn = player1;
        this.moves = 0;
        this.matches = 0;
    }

    public Board board() {
        return board;
    }

    public Player player1() {
        return player1;
    }

    public Player player2() {
        return player2;
    }

    public int winScore() {
        return winScore;
    }

    public Player currentPlayer() {
        return turn;
    }

    public int moves() {
        return moves;
    }

    public int matches() {
        return matches;
    }

    /** Accuracy as percentage in range [0, 100]. */
    public double accuracyPercent() {
        if (moves == 0)
            return 0.0;
        return (matches * 100.0) / moves;
    }

    public boolean isGameOver() {
        return turn.hasWon(winScore);
    }

    public TurnResult playMove(Position position1, Position position2) {
        board.open(position1);
        board.open(position2);

        moves++;

        boolean match = board.play(position1, position2);
        if (match) {
            matches++;
            turn.increaseScore();
        } else {
            turn = (turn == player1) ? player2 : player1;
        }

        return new TurnResult(
                match,
                turn,
                player1.getScore(),
                player2.getScore(),
                isGameOver(),
                moves,
                matches,
                accuracyPercent());
    }
}