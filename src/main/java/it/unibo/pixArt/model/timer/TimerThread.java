package it.unibo.pixArt.model.timer;

public class TimerThread extends Thread {

    private final GameTimer timer;
    private final Runnable callback;
    private final Runnable remainingTime;
    private final long TIMESLEEP = 200;

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

    /**
     * Run timer
     */
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
