package it.unibo.pixArt.model.game;

import it.unibo.pixArt.model.timer.GameTimer;

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
