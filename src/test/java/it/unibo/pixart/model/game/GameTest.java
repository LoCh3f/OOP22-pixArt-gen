package it.unibo.pixart.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.game.builder.GameBuilderImpl;
import it.unibo.pixart.model.timer.GameTimer;
import it.unibo.pixart.model.timer.GameTimerImpl;

/**The Test class of the Game.
 */
class GameTest {
    private static GameType gameType = GameType.MIRROR;
    private static GameTimer gameTimer = new GameTimerImpl(100);
    private final Game testGame = new GameBuilderImpl().gameType(gameType).gameTimer(gameTimer).build();

    @Test
    void gameTimerGetterTest() {
        assertEquals(gameTimer, testGame.getGameTimer());
    }

    @Test
    void gameTypeGetterTest() {
        assertEquals(gameType, testGame.getGameType());
    }
}

