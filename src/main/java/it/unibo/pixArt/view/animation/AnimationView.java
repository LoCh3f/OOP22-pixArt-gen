package it.unibo.pixArt.view.animation;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.animation.AnimationController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;

public class AnimationView extends AbstractFXView {

    @FXML
    private ChoiceBox<String> dimensionChoice;

    @FXML
    private ChoiceBox<String> directionChoice;

    @FXML
    private ImageView imageContainer;

    @FXML
    public void switchAnimation() {
        /**Start/ stop animations */
    }

    @FXML
    public void onWorkSpaceClick() {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
    }

    @Override
    public void init() {
        this.dimensionChoice.getItems().addAll(this.getAnimationController().getListSizes());
        this.dimensionChoice.setValue(this.getAnimationController().getListSizes().get(0));
        this.directionChoice.getItems().addAll(this.getAnimationController().getListDirections());
        this.directionChoice.setValue(this.getAnimationController().getListDirections().get(0));
    }

    public void animateImage(final String path) {
        //Image.setImage(path)
    }

    private AnimationController getAnimationController() {
        return (AnimationController) this.getController();
    }

}
