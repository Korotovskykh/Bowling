package org.enigma.bowling;

import org.junit.Before;
import org.junit.Test;

public class BowlingTest {

    Game game;

    @Before
    public void setUp() {
        initGame();
    }

    @Test
    public void perfectGame() {
        while (!game.isOver()) {
            game.roll(10);
        }
        assert(game.getScore() == 300);
    }

    @Test
    public void shouldFail() {
        while (!game.isOver()) {
            game.roll(5);
        }
        assert(game.getScore() == 150);
    }


    private void initGame() {
        game = new Game();
    }
}
