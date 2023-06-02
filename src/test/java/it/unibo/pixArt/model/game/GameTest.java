package it.unibo.pixArt.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.game.builder.GameBuilderImpl;
import it.unibo.pixArt.model.timer.GameTimer;
import it.unibo.pixArt.model.timer.GameTimerImpl;

public class GameTest {
    private static GameType GAME_TYPE = GameType.MIRROR;
    private static GameTimer GAME_TIMER = new GameTimerImpl(100);
    private final static Game TEST_GAME = new GameBuilderImpl().gameType(GAME_TYPE).gameTimer(GAME_TIMER).build();

    @Test
    void getGameTimer(){
        assertEquals(GAME_TIMER, TEST_GAME.getGameTimer());
    }

    @Test
    void getGameType(){
        assertEquals(GAME_TYPE, TEST_GAME.getGameType());
    }
}

