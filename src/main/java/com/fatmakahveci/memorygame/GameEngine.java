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

    public GameEngine(Board board, Player player1, Player player2, int winScore) {
        this.board = Objects.requireNonNull(board);
        this.player1 = Objects.requireNonNull(player1);
        this.player2 = Objects.requireNonNull(player2);
        this.winScore = winScore;
        this.turn = player1;
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

    public boolean isGameOver() {
        return turn.hasWon(winScore);
    }

    /**
     * Plays a move with two already-validated, distinct, playable positions.
     * UI layer is responsible for validation and showing the board.
     */
    public TurnResult playMove(Position position1, Position position2) {
        board.open(position1);
        board.open(position2);

        boolean match = board.play(position1, position2);
        if (match) {
            turn.increaseScore();
        } else {
            turn = (turn == player1) ? player2 : player1;
        }

        return new TurnResult(match, turn, player1.getScore(), player2.getScore(), isGameOver());
    }
}