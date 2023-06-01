package it.unibo.pixArt.model.timer;

public interface GameTimer {

    /**
     * starts the timer
     */
    void start();

    /**
     * stop the timer
     */
    void stop();

    /**
     * @return the remaining time
     */
    double getRemainingTime();

    /**
     * @return true if the timer is running
     */
    boolean isRunning();

    /**
     * @return true if the time is over
     */
    boolean isTimeOver();

}
