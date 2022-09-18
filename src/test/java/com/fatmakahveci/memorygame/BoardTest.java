package com.fatmakahveci.memorygame;

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
}
