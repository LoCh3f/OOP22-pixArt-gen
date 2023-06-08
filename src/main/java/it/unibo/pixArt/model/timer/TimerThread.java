package it.unibo.pixArt.model.timer;

public final class TimerThread extends Thread {

    private static final long TIMESLEEP = 200;

    private final GameTimer timer;
    private final Runnable callback;
    private final Runnable remainingTime;

    /**
     * @param timer
     * @param callback
     * @param remainingTime
     */
    public TimerThread(final GameTimer timer, final Runnable callback, final Runnable remainingTime) {
        this.timer = timer;
        this.callback = callback;
        this.remainingTime = remainingTime;
    }

    @Override
    public void run() {
        while (this.timer.isRunning()) {

            if (this.timer.isTimeOver()) {
                this.callback.run();
                break;
            }

            remainingTime.run();

            try {
                Thread.sleep(TIMESLEEP);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
