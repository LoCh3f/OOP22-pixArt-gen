package it.unibo.pixArt.controller.animation;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.view.animation.AnimationView;
import javafx.scene.control.skin.TextInputControlSkin.Direction;


public class AnimationControllerImpl extends SimpleController implements AnimationController {
    private List<HistoryFrame> historyFrames;
    private Boolean isRunning = false;
    private Directions animationDirection = Directions.FORWARD;
    private int index;

    public AnimationControllerImpl() {
        //Initialize the historyFrames list by getting each HistoryFrame from the project.
    }

    private class Animator extends Thread {
        @Override
        public void run() {
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

    @Override
    public void setAnimationDirection(final String newDir) {
        this.animationDirection = Directions.getListDirections().stream().filter(e -> e.getName() == newDir).findAny().get();
    }

    @Override
    public void setFrameDuration(final int frameIndex, final int duration) {
        this.historyFrames.get(frameIndex).setAnimationDuration(duration);
    }

    @Override
    public void setAnimationIsRunning() {
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
        final int prev = this.index;
        this.index = this.index + 1;
        System.out.println(this.index);
        return historyFrames.get(prev);
    }

    private AnimationView getAnimationView() {
        return (AnimationView) this.getView();
    }

}
