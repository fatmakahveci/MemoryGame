package com.fatmakahveci.memorygame;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CellTest {

    @Test
    public void testIsPlayableForOpenCell() {
        Cell cell = new Cell('a');
        assertTrue(cell.isPlayable());
    }

    @Test
    public void testIsPlayableForClosedCell() {
        Cell cell = new Cell('a');
        cell.open();
        cell.close();
        assertTrue(cell.isPlayable());
    }

    @Test
    public void testIsPlayableForOpenedCell() {
        Cell cell = new Cell('a');
        cell.open();
        assertFalse(cell.isPlayable());
    }

    @Test
    public void testIsSameWith() {
        Cell firstCell = new Cell('a');
        Cell secondCell = new Cell('a');
        assertTrue(firstCell.isSameWith(secondCell));
    }

    @Test
    public void testIsSameWithForDifferentCells() {
        Cell firstCell = new Cell('a');
        Cell secondCell = new Cell('b');
        assertFalse(firstCell.isSameWith(secondCell));
    }
}
