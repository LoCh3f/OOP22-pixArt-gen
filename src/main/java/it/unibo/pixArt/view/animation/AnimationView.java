package it.unibo.pixArt.view.animation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import it.unibo.pixArt.controller.animation.AnimationController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
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
        this.getAnimationController().setAnimationIsRunning();
        this.getAnimationController().setAnimationDirection(this.directionChoice.getValue());
    }

    @FXML
    public void onWorkSpaceClick() {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
    }

    @Override
    public void init() {
        this.dimensionChoice.getItems().addAll(this.getAnimationController().getListSizes());
        this.dimensionChoice.setValue(this.getAnimationController().getListSizes().get(0));
        this.directionChoice.getItems().addAll(this.getAnimationController().getListDirections().stream().map(e -> e.getName()).toList());
        this.directionChoice.setValue(this.getAnimationController().getListDirections().get(0).getName());
    }

    public void displayImage(final String path) {
        imageContainer.setImage(new Image(path));
    }

    private AnimationController getAnimationController() {
        return (AnimationController) this.getController();
    }

}
