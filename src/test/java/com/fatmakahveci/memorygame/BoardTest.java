package com.fatmakahveci.memorygame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardTest {

    @Test(expected = IllegalArgumentException.class)
    public void testGameInitFailOnInvalidOddSizes() {
        new Board(3, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameInitFailOnInvalidZeroSizes() {
        new Board(0, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameInitFailOnInvalidNegativeRowSizes() {
        new Board(-1, 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameInitFailOnInvalidNegativeColSizes() {
        new Board(1, -2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameInitExceedMaximumSizes() {
        new Board(26, 26);
    }

    @Test
    public void testGameInitSuccessOnValidSizes() {
        new Board(2, 4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnitializedBoardWithValidSize() {
        new Board(new Cell[2][4]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGameInitSuccessOnInvalidSizesCellConstructor() {
        new Board(new Cell[3][3]);
    }

    @Test
    public void testPlayMatch() {
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = new Cell('a');
        cells[0][1] = new Cell('a');
        cells[1][0] = new Cell('b');
        cells[1][1] = new Cell('b');
        Board board = new Board(cells);

        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(0, 1);

        board.open(pos1);
        board.open(pos2);
        boolean match = board.play(pos1, pos2);
        assertTrue(match);
        assertFalse(board.isPlayable(pos1));
        assertFalse(board.isPlayable(pos2));
    }

    @Test
    public void testPlayNonMatch() {
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = new Cell('a');
        cells[0][1] = new Cell('a');
        cells[1][0] = new Cell('b');
        cells[1][1] = new Cell('b');
        Board board = new Board(cells);

        Position pos1 = new Position(0, 0);
        Position pos2 = new Position(1, 1);

        board.open(pos1);
        board.open(pos2);
        boolean match = board.play(pos1, pos2);

        assertFalse(match);
        assertTrue(board.isPlayable(pos1));
        assertTrue(board.isPlayable(pos2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPlaySamePositionThrows() {
        Cell[][] cells = new Cell[2][2];
        cells[0][0] = new Cell('a');
        cells[0][1] = new Cell('a');
        cells[1][0] = new Cell('b');
        cells[1][1] = new Cell('b');
        Board board = new Board(cells);

        Position pos = new Position(0, 0);
        board.open(pos);
        board.play(pos, pos);
    }

    @Test
    public void testInitBoardWithRandomHasDeterministicLayout() {
        Board board = new Board(2, 4);
        board.initBoard(new java.util.Random(12345));

        for (int r = 0; r < board.rows(); r++) {
            for (int c = 0; c < board.cols(); c++) {
                board.open(new Position(r, c));
            }
        }

        String rendered = board.toString().replaceAll("[^A-Za-z]", "");
        assertEquals(8, rendered.length());
        assertEquals(4, rendered.chars().distinct().count());
    }
}
