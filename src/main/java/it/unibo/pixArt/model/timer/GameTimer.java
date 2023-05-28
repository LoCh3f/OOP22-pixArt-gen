package it.unibo.pixArt.model.timer;

public interface GameTimer {

    public void start();

    public void stop();

    public double getRemainingTime();

    public boolean isRunning();

    public boolean isTimeOver();

}
