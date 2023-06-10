package it.unibo.pixart.controller.animation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.pixart.controller.SimpleController;
import it.unibo.pixart.model.historyframe.HistoryFrame;
import it.unibo.pixart.utilities.FileHandler;
import it.unibo.pixart.utilities.ImagePrinter;
import it.unibo.pixart.view.animation.AnimationView;
import javafx.scene.control.ChoiceDialog;

/**
 * Implementation for AnimationController.
 */
public final class AnimationControllerImpl extends SimpleController implements AnimationController {
    private Boolean isRunning = false;
    private int index = 0;

    private class Animator extends Thread {
        @Override
        public void run() {
            while (isRunning) {
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
    public void setFrameDuration(final int frameIndex, final int duration) {
        this.getModel().getProject().getAllHistoryFrames().get(frameIndex).setAnimationDuration(duration);
    }

    @Override
    public void setAnimationIsRunning() {
        if (!this.isRunning) {
            this.index = 0;
            final Thread th = new Animator();
            th.start();
        }
        this.isRunning = !this.isRunning;
    }

    @Override
    public HistoryFrame getCurrentImage() { 
        if (this.index == getHistoryFrames().size()  || this.index == 0) {
            this.index = 0;
        }
        final int prev = this.index;
        this.index = this.index + 1;
        return getHistoryFrames().get(prev);
    }

    @Override
    public List<HistoryFrame> getHistoryFrames() {
        return this.getModel().getProject().getAllHistoryFrames();
    }

    @Override
    public boolean getAnimationIsRunning() {
        return this.isRunning;
    }

    @Override
    public void saveProject() {
        List<String> choices = new ArrayList<>();
        choices.add("1");
        choices.add("4");
        choices.add("16");

        ChoiceDialog<String> dialog = new ChoiceDialog<>("1", choices);
        dialog.setTitle("Scelta scala");
        dialog.setHeaderText(null);
        dialog.setContentText("Seleziona la scala:");

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            ImagePrinter.getInstance().printAllFrames(getModel().getProject(), Integer.parseInt(result.get()));
        try {
            FileHandler.getInstance().fromProjectToJson(this.getModel().getProject());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        }
    }

    private AnimationView getAnimationView() {
        return (AnimationView) this.getView();
    }

}
