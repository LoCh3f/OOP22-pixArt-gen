package it.unibo.pixArt.model.timer;

public final class GameTimerImpl implements GameTimer {

    private boolean isRunning = true;
    private final long timerType;
    private long initialTime;

    public GameTimerImpl(final long timerType) {
        this.timerType = timerType;
    }

    @Override
    public void start() {
        initialTime = System.currentTimeMillis() / 1000L; 
        isRunning = true;
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    @Override
    public double getRemainingTime() {
        final long elapsedTime = System.currentTimeMillis() / 1000L - initialTime;
        return this.timerType - elapsedTime;
    }

    @Override
    public boolean isRunning() {
        return this.isRunning;
    }

    @Override
    public boolean isTimeOver() {
        return getRemainingTime() < 0;
    }
 
}
