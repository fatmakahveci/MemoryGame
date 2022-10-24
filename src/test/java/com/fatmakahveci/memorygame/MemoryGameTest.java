package com.fatmakahveci.memorygame;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class MemoryGameTest {
    private Board board = mock(Board.class);
    private Input in = mock(Input.class);
    private int winScore = 2 * 2 / 2;
    private Player player1 = new Player("Player 1", winScore - 1);
    private Player player2 = new Player("Player 2");
    private Position position1 = new Position(0, 0);
    private Position position2 = new Position(1, 1);
    private MemoryGame game = new MemoryGame(board, player1, player2, winScore, in);

    @Before
    public void setUp() {
        when(in.nextPositionInput()).thenReturn(position1).thenReturn(position2);
        when(board.isCellValid(position1)).thenReturn(true);
        when(board.isPlayable(position1)).thenReturn(true);
        when(board.isCellValid(position2)).thenReturn(true);
        when(board.isPlayable(position2)).thenReturn(true);
    }

    @Test
    public void testWholeGame() {
        doNothing().when(board).initBoard();

        when(board.play(new Position(0, 0), new Position(1, 1))).thenReturn(true);

        game.play();
    }

    @Test
    public void testPlayOnceWithMatch() {
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();

        when(board.play(position1, position2)).thenReturn(true);

        game.playOnce();

        assertEquals(game.whoseTurn(), player1);
        assertEquals(player1Score + 1, player1.getScore());
        assertEquals(player2Score, player2.getScore());
    }

    @Test
    public void testPlayOnceWithoutMatch() {
        int player1Score = player1.getScore();
        int player2Score = player2.getScore();

        when(board.play(position1, position2)).thenReturn(false);

        game.playOnce();

        assertEquals(game.whoseTurn(), player2);
        assertEquals(player1Score, player1.getScore());
        assertEquals(player2Score, player2.getScore());
    }

    @Test
    public void testInvalidInput() {
        Position invalidPosition = new Position(-1, -1);
        when(in.nextPositionInput()).thenReturn(invalidPosition).thenReturn(position1).thenReturn(position2);
        when(board.isCellValid(invalidPosition)).thenReturn(false);
        when(board.isPlayable(invalidPosition)).thenReturn(false);
        when(board.isCellValid(position1)).thenReturn(true);
        when(board.isPlayable(position1)).thenReturn(true);
        when(board.isCellValid(position2)).thenReturn(true);
        when(board.isPlayable(position2)).thenReturn(true);

        int player1Score = player1.getScore();
        int player2Score = player2.getScore();

        when(board.play(position1, position2)).thenReturn(true);

        game.playOnce();

        assertEquals(game.whoseTurn(), player1);
        assertEquals(player1Score + 1, player1.getScore());
        assertEquals(player2Score, player2.getScore());
    }
}
