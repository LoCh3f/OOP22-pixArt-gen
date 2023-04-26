package it.unibo.pixArt.controller.animation;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.model.animation.AnimationModel;
import it.unibo.pixArt.model.animation.AnimationModelImpl;
import it.unibo.pixArt.view.View;
import it.unibo.pixArt.view.animation.AnimationView;

public class AnimationControllerImpl extends SimpleController implements AnimationController {
    private AnimationView view = new AnimationView();
    private AnimationModel model = new AnimationModelImpl();
    private Thread th;


    public class Animator implements Runnable {

        @Override
        public void run() {
            while(getModel().getPause()) {
                var frame = getModel().getCurrentFrame();
                getView().animateImage(frame.getPath());
                try {
                    Thread.sleep(frame.getAnimationDuration());
                } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }
    
   
    public AnimationModel getModel() {
        return this.model;
    }

    public AnimationView getView() {
        return this.view;
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
        if(!this.model.getPause()){
            Animator anim = new Animator();
            this.th = new Thread(anim);
            this.th.start();
        }
        this.model.setPause();
    }

}
