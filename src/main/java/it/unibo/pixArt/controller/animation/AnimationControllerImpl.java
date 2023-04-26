package it.unibo.pixArt.controller.animation;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.model.animation.AnimationModel;
import it.unibo.pixArt.model.animation.AnimationModelImpl;
import it.unibo.pixArt.view.View;
import it.unibo.pixArt.view.animation.AnimationView;

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

    private AnimationModel getAnimationModel() {
        return (AnimationModel) this.getModel();
    }

}
