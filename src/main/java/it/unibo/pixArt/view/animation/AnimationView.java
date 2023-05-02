package it.unibo.pixArt.view.animation;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.animation.AnimationController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class AnimationView extends AbstractFXView {
    private List<String> directions;
    private List<String> previewSizes;

    @FXML
    private ChoiceBox<String> dimensionChoice;

    @FXML
    private ChoiceBox<String> directionChoice;

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
        this.directions = new LinkedList<>(this.getAnimationController().getListDirections());
        this.previewSizes = new LinkedList<>(this.getAnimationController().getListSizes());
        this.dimensionChoice.getItems().addAll(this.previewSizes);
        this.dimensionChoice.setValue(this.previewSizes.get(0));
        this.directionChoice.getItems().addAll(this.directions);
        this.directionChoice.setValue(this.directions.get(0));
    }

    public void animateImage(final String path) {
        //Image.setImage(path)
    }

    private AnimationController getAnimationController() {
        return (AnimationController) this.getController();
    }

}
