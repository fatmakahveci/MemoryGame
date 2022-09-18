package com.fatmakahveci.memorygame;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PositionTest {

    @Test
    public void testEquals() {
        Position p1 = new Position(5, 5);
        Position p2 = new Position(5, 5);

        assertTrue(p1.equals(p2));
    }

}
