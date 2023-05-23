package it.unibo.pixArt.model.timer;

import java.util.Timer;
import java.util.TimerTask;

public class IncTimerImpl implements GameTimer{

    private boolean isRunning;
    private double elapsedTime;
    private final Timer timer;
    private TimerTask task;

    public IncTimerImpl(){
        this.isRunning = true;
        this.elapsedTime = 0;
        this.timer = new Timer();
    }

    public double getElapsedTime(){
        return this.elapsedTime;
    }

    @Override
    public void start() {
        if (!this.isRunning){
            this.isRunning = true;
            task = new TimerTask() {
                @Override
                public void run(){
                    elapsedTime++;
                }
            };
            this.timer.scheduleAtFixedRate(task, 0, 1000);
        }
    }

    @Override
    public void stop() {
        if (this.isRunning) {
            this.isRunning = false;
            this.task.cancel();
        }
    }
    
}
