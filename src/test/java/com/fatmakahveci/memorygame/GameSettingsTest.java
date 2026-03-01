package com.fatmakahveci.memorygame;

import static org.junit.Assert.*;

import org.junit.Test;

public class GameSettingsTest {

    @Test
    public void testWinScore() {
        GameSettings s = new GameSettings(2, 2);
        assertEquals(2, s.winScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testOddSizeRejected() {
        new GameSettings(3, 3); // 9 is odd
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTooLargeRejected() {
        new GameSettings(8, 8); // 64 > 52
    }
}