package it.unibo.pixart.model.game;

import it.unibo.pixart.model.timer.GameTimer;

/** Theinterface of the Game.
 */
public interface Game {

    /**
     * @return the game timer
     */
    GameTimer getGameTimer();

    /**
     * @return get the game's type.
     */
    GameType getGameType();
}
