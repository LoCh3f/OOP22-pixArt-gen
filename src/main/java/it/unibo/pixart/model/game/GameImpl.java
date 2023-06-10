package it.unibo.pixart.model.game;

import it.unibo.pixart.model.timer.GameTimer;

/**
 * The implementation class of the Game
 */
public final class GameImpl implements Game {
    private final GameType gameType;
    private final GameTimer gameTimer;

    /**
     * The game constructor
     * @param gameType
     * @param gameTimer
     */
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
