package it.unibo.pixArt.controller.animation;

import it.unibo.pixArt.model.animation.AnimationModel;
import it.unibo.pixArt.model.animation.AnimationModelImpl;
import it.unibo.pixArt.view.impl.AnimationView;

public class AnimationControllerImpl implements AnimationController{
    private AnimationView view = new AnimationView();
    private AnimationModel model = new AnimationModelImpl();


    @Override
    public void getImage(String path) {
        int counter = 0;
        while(this.model.getPause()) {
            var currentFrame = this.model.getCurrentFrame(counter);
            this.view.animateImage(currentFrame.getPath());
            try {
                Thread.sleep(currentFrame.getAnimationDuration());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            counter = counter + this.model.getDirection().value;
        }
        
    }


    @Override
    public void setAnimationDirection(String newDir) {
       this.model.setDirection(newDir);
    }


    @Override
    public void setFrameDuration(int frameIndex, int duration) {
       this.model.selectFrameDuration(frameIndex, duration);
    }


    @Override
    public void setAnimationPause() {
        this.model.setPause();
    }

}
