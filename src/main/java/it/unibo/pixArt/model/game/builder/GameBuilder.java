package it.unibo.pixArt.model.game.builder;

import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.game.GameType;
import it.unibo.pixArt.model.timer.GameTimer;

public interface GameBuilder {

    /**
     * @param gameType the type of the game
     * @return the game builder
     */
    GameBuilder gameType(final GameType gameType);

    /**
     * @param gameTimer the timer of the game
     * @return the game builder
     */
    GameBuilder gameTimer(final GameTimer gameTimer);

    /**
     * @return the game builded with the builder
     */
    Game build();
}
