package com.fatmakahveci.memorygame;

import java.util.Objects;

/**
 * Console-specific UI. Handles input/output, delegates rules to GameEngine.
 */
public class ConsoleUI {

    private final GameEngine engine;
    private final Input in;

    public ConsoleUI(GameEngine engine, Input in) {
        this.engine = Objects.requireNonNull(engine);
        this.in = Objects.requireNonNull(in);
    }

    public void run() {
        engine.board().initBoard();
        System.out.println(engine.board());

        while (!engine.isGameOver()) {
            playRound();
        }

        Player winner = engine.currentPlayer();
        System.out.println(winner.getName() + " has won the game!");
        System.out.println("Final Score: " + engine.player1().getName() + " " + engine.player1().getScore()
                + " - " + engine.player2().getScore() + " " + engine.player2().getName());
        System.out.println("Moves: " + engine.moves()
                + " | Matches: " + engine.matches()
                + " | Accuracy: " + String.format("%.1f", engine.accuracyPercent()) + "%");
    }

    private void playRound() {
        printTurnHeader();

        Position p1 = readValidPosition();
        System.out.println(engine.currentPlayer().getName() + " picked " + p1 + "\n");

        Position p2 = readValidPositionDistinctFrom(p1);
        System.out.println(engine.currentPlayer().getName() + " picked " + p2 + "\n");

        TurnResult result = engine.playMove(p1, p2);

        System.out.println(engine.board());

        if (result.match()) {
            System.out.println("Match! " + result.nextTurn().getName() + " gets a point.\n");
        } else {
            System.out.println("No match.");
            waitForEnter();
        }
    }

    private void printTurnHeader() {
        Player turn = engine.currentPlayer();
        System.out.println("==================================");
        System.out.println("Turn: " + turn.getName()
                + " | Score: " + engine.player1().getName() + " " + engine.player1().getScore()
                + " - " + engine.player2().getScore() + " " + engine.player2().getName());
        System.out.println("Moves: " + engine.moves()
                + " | Matches: " + engine.matches()
                + " | Accuracy: " + String.format("%.1f", engine.accuracyPercent()) + "%");
        System.out.println("==================================\n");
    }

    private void waitForEnter() {
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.println();
    }

    private Position readValidPosition() {
        while (true) {
            Position position = in.nextPositionInput();

            if (!engine.board().isCellValid(position)) {
                System.out.println("Cell position is not valid. " + validRangeMessage());
                System.out.println("Try again...\n");
                continue;
            }

            if (!engine.board().isPlayable(position)) {
                System.out.println("Cell is already open. Try another cell.\n");
                continue;
            }

            return position;
        }
    }

    private Position readValidPositionDistinctFrom(Position first) {
        while (true) {
            Position p = readValidPosition();
            if (p.equals(first)) {
                System.out.println("You picked the same cell twice. Choose a different cell.\n");
                continue;
            }
            return p;
        }
    }

    private String validRangeMessage() {
        return "Valid range: row 0.." + (engine.board().rows() - 1)
                + ", col 0.." + (engine.board().cols() - 1) + ".";
    }
}