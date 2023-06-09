package it.unibo.pixArt.model.game;

import it.unibo.pixArt.model.timer.GameTimer;

public final class GameImpl implements Game {
    private GameType gameType;
    private GameTimer gameTimer;

    public GameImpl(final GameType gameType, final GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        this.gameType = gameType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameType getGameType() {
        return this.gameType;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameTimer getGameTimer() {
        return this.gameTimer;
    }
}
