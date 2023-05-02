package it.unibo.pixArt.controller.animation;

import java.util.List;

import it.unibo.pixArt.controller.SimpleController;


public class AnimationControllerImpl extends SimpleController implements AnimationController {

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
