package it.unibo.pixart.model.game.builder;

import it.unibo.pixart.model.game.Game;
import it.unibo.pixart.model.game.GameImpl;
import it.unibo.pixart.model.game.GameType;
import it.unibo.pixart.model.timer.GameTimer;
/** The implementation of the GameBuilder.
 */
public final class GameBuilderImpl implements GameBuilder {

    private GameType gameType;
    private GameTimer gameTimer;

    /**
     * {@inheritDoc}}
     */
    @Override
    public GameBuilder gameType(final GameType gameType) {
        this.gameType = gameType;
        return this;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public GameBuilder gameTimer(final GameTimer gameTimer) {
        this.gameTimer = gameTimer;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Game build() {
        return new GameImpl(this.gameType, this.gameTimer);
    }
}
