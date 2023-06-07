package it.unibo.pixArt.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.game.builder.GameBuilderImpl;
import it.unibo.pixArt.model.timer.GameTimer;
import it.unibo.pixArt.model.timer.GameTimerImpl;

public class GameTest {
    private static GameType gameType = GameType.MIRROR;
    private static GameTimer gameTimer = new GameTimerImpl(100);
    private final Game testGame = new GameBuilderImpl().gameType(gameType).gameTimer(gameTimer).build();

    @Test
    void getGameTimer() {
        assertEquals(gameTimer, testGame.getGameTimer());
    }

    @Test
    void getGameType() {
        assertEquals(gameType, testGame.getGameType());
    }
}

