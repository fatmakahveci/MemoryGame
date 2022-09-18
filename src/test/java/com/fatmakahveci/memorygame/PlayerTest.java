package com.fatmakahveci.memorygame;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PlayerTest {

    @Test
    public void testIncreaseScore() {
        Player player = new Player("player", 10);
        player.increaseScore();
        assertEquals(11, player.getScore());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeValuedScore() {
        new Player("player", -10);
    }

    @Test
    public void testHasWon() {
        Player player = new Player("player", 10);
        assertTrue(player.hasWon(10));
    }

    @Test
    public void testHasNotWonYet() {
        Player player = new Player("player", 5);
        assertFalse(player.hasWon(10));
    }
}
