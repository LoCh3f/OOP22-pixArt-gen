package it.unibo.pixArt.model.animation;

import java.util.List;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.project.Project;

public class AnimationModelImpl extends ModelImpl implements AnimationModel {

    private List<HistoryFrame> frames;
    private boolean inPause = true;
    private Directions direction = Directions.FORWARD;
    private int counter = 0;

    public AnimationModelImpl(final Project project) {
        super(null, null, project);
    }
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
        if(direction == Directions.FORWARD.getName()) {
            this.direction = Directions.FORWARD;
        } else if (direction == Directions.BACKWARD.getName()) {
            this.direction = Directions.BACKWARD;
        }
    }

    @Override
    public HistoryFrame getCurrentFrame() {
        int index;
	    if(this.counter == this.frames.size() - 1) {
		    this.counter = 0;
	    }
	    index = this.counter;
	    this.counter = this.counter + 1;
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
