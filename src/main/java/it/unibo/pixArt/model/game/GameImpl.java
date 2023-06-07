package it.unibo.pixArt.model.game;

import it.unibo.pixArt.model.timer.GameTimer;

public class GameImpl implements Game {
    private GameType gameType;
    private GameTimer gameTimer;

    public GameImpl(final GameType gameType, final GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        this.gameType = gameType;
    }

    @Override
    public GameType getGameType() {
        return this.gameType;
    }

    @Override
    public GameTimer getGameTimer() {
        return this.gameTimer;
    }
}
