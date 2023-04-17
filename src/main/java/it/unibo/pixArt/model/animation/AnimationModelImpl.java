package it.unibo.pixArt.model.animation;

import java.util.List;

import it.unibo.pixArt.controller.animation.AnimationControllerImpl;
import it.unibo.pixArt.model.historyframe.HistoryFrame;

public class AnimationModelImpl implements AnimationModel {

    private List<HistoryFrame> frames;
    private boolean inPause = true;
    private Directions direction = Directions.FORWARD;

    @Override
    public void selectFrameDuration(final int frameIndex,final int duration) {
        this.frames.get(frameIndex).setAnimationDuration(duration);
    }

    @Override
    public void setPause() {
        this.inPause = !this.inPause;
    }

    @Override
    public void setDirection(final String direction) {
        //Da migliorare
        if(direction == "Forward") {
            this.direction = Directions.FORWARD;
        } else if (direction == "Backward") {
            this.direction = Directions.BACKWARD;
        }
    }

    @Override
    public HistoryFrame getCurrentFrame(final int index) {
        return this.frames.get(index);
    }

    @Override
    public boolean getPause() {
        return this.inPause;
    }

    @Override
    public Directions getDirection() {
       return this.direction;
    }
    
}
