package com.fatmakahveci.memorygame;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameEngineTest {

    private static final int WIN_SCORE = 2;

    private Player player1;
    private Player player2;

    private final Position p00 = new Position(0, 0);
    private final Position p11 = new Position(1, 1);

    @Before
    public void setUp() {
        // Keep same behaviour as your earlier tests:
        // player1 starts with WIN_SCORE - 1, so 1 match ends the game
        player1 = new Player("Player 1", WIN_SCORE - 1);
        player2 = new Player("Player 2");
    }

    @Test
    public void testPlayMoveWithMatch_IncreasesScoreAndKeepsTurn() {
        Board board = presetBoard(new char[][]{
                {'X', 'A'},
                {'B', 'X'}
        });

        GameEngine engine = new GameEngine(board, player1, player2, WIN_SCORE);

        int p1 = player1.getScore();
        int p2 = player2.getScore();

        TurnResult result = engine.playMove(p00, p11);

        assertTrue(result.match());
        assertEquals(p1 + 1, player1.getScore());
        assertEquals(p2, player2.getScore());

        // match => current player keeps the turn
        assertEquals(player1, engine.currentPlayer());
        assertEquals(player1, result.nextTurn());
    }

    @Test
    public void testPlayMoveWithoutMatch_SwitchesTurnAndKeepsScores() {
        Board board = presetBoard(new char[][]{
                {'A', 'X'},
                {'Y', 'B'}
        });

        GameEngine engine = new GameEngine(board, player1, player2, WIN_SCORE);

        int p1 = player1.getScore();
        int p2 = player2.getScore();

        TurnResult result = engine.playMove(p00, p11);

        assertFalse(result.match());
        assertEquals(p1, player1.getScore());
        assertEquals(p2, player2.getScore());

        // mismatch => turn switches
        assertEquals(player2, engine.currentPlayer());
        assertEquals(player2, result.nextTurn());
    }

    @Test
    public void testGameOver_WhenCurrentPlayerReachesWinScore() {
        Board board = presetBoard(new char[][]{
                {'Z', 'A'},
                {'B', 'Z'}
        });

        GameEngine engine = new GameEngine(board, player1, player2, WIN_SCORE);

        assertFalse(engine.isGameOver());

        TurnResult result = engine.playMove(p00, p11);

        assertTrue(result.match());
        assertTrue(engine.isGameOver());
        assertTrue(result.gameOver());
        assertTrue(player1.hasWon(WIN_SCORE));
    }

    private static Board presetBoard(char[][] symbols) {
        int rows = symbols.length;
        int cols = symbols[0].length;

        Cell[][] cells = new Cell[rows][cols];
        for (int r = 0; r < rows; r++) {
            if (symbols[r].length != cols) {
                throw new IllegalArgumentException("Preset must be rectangular.");
            }
            for (int c = 0; c < cols; c++) {
                cells[r][c] = new Cell(symbols[r][c]);
            }
        }
        return new Board(cells); // preset constructor => initialized
    }
}