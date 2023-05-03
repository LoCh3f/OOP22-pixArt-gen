package it.unibo.pixArt.controller.animation;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.historyframe.HistoryFrame;


public class AnimationControllerImpl extends SimpleController implements AnimationController {
    private List<HistoryFrame> historyFrames;

    public AnimationControllerImpl() {
        //Initialize the historyFrames list by getting each HistoryFrame from the project.
    }

    @Override
    public void setAnimationDirection(String newDir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAnimationDirection'");
    }

    @Override
    public void setFrameDuration(int frameIndex, int duration) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFrameDuration'");
    }

    @Override
    public void setAnimationPause() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAnimationPause'");
    }

    @Override
    public List<String> getListSizes() {
        return PreviewSizes.getListSizes();
    }

    @Override
    public List<String> getListDirections() {
        return Directions.getListDirections();
    }


}
