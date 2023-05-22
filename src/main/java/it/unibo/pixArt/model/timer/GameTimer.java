package it.unibo.pixArt.model.timer;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {

    private boolean isRunning;
    private double remainingTime; 
    private final Timer timer;
    private TimerTask task;

    public GameTimer(final double remainingTime){
        this.isRunning = true;
        this.remainingTime = remainingTime;
        this.timer = new Timer();
    }

    public double getRemainingTime(){
        return this.remainingTime;
    }

    public void start(){
        if (!this.isRunning){
            this.isRunning = true;
            task = new TimerTask() {
                @Override
                public void run(){
                    remainingTime--;
                }
            };
            this.timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }

    public void stop(){
        if (this.isRunning) {
            this.isRunning = false;
            this.task.cancel();
        }
    }

}
