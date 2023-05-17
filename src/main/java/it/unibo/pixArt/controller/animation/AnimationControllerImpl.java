package it.unibo.pixArt.controller.animation;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.historyframe.HistoryFrameImpl;
import it.unibo.pixArt.view.animation.AnimationView;


public class AnimationControllerImpl extends SimpleController implements AnimationController {
    private Boolean isRunning = false;
    private Directions animationDirection = Directions.FORWARD;
    private int index = 0;
    private static final String TOAD_IMAGE = "/image/toad.png";
    private static final String SONIC_IMAGE = "/image/sonic.jpg";
    private static final String HOMER_IMAGE = "/image/homer.jpg";
    HistoryFrame h1 = new HistoryFrameImpl(TOAD_IMAGE);
    HistoryFrame h2 = new HistoryFrameImpl(HOMER_IMAGE);
    HistoryFrame h3 = new HistoryFrameImpl(SONIC_IMAGE);
    public final List<HistoryFrame> imagePaths = new LinkedList<HistoryFrame>(List.of(h1,h2,h3));

    public AnimationControllerImpl() {
        //Initialize the historyFrames list by getting each HistoryFrame from the project.
    }

    private class Animator extends Thread {
        @Override
        public void run() {
            while(isRunning){
                final HistoryFrame currentFrame = getCurrentImage();
                getAnimationView().displayImage(currentFrame.getPath());
        
                try {
                    Thread.sleep(currentFrame.getAnimationDuration());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setAnimationDirection(final String newDir) {
        this.animationDirection = Directions.getListDirections().stream().filter(e -> e.getName() == newDir).findAny().get();
    }

    @Override
    public void setFrameDuration(final int frameIndex, final int duration) {
        this.getModel().getProject().getAllHistoryFrames().get(frameIndex).setAnimationDuration(duration);
        //imagePaths.get(frameIndex).setAnimationDuration(duration);
    }

    @Override
    public void setAnimationIsRunning() {
        if(!this.isRunning) {
            this.index = 0;
            final Thread th = new Animator();
            th.start();
        }
        this.isRunning = !this.isRunning;
    }

    @Override
    public List<String> getListSizes() {
        return PreviewSizes.getListSizes();
    }

    @Override
    public List<Directions> getListDirections() {
        return Directions.getListDirections();
    }

    @Override
    public HistoryFrame getCurrentImage() { 
        if(this.index == 3 || this.index == 0) {
            this.index = 0;
        }
        final int prev = this.index;
        this.index = this.index + animationDirection.getValue();
        return getHistoryFrames().get(prev); //this.getModel().getProject().getAllFrames().get(prev).getHistoryFrame();
    }

    @Override
    public List<HistoryFrame> getHistoryFrames() {
        return this.getModel().getProject().getAllHistoryFrames();
    }

    private AnimationView getAnimationView() {
        return (AnimationView) this.getView();
    }

    @Override
    public boolean getAnimationIsRunning() {
        return this.isRunning;
    }

}
