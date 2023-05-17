package it.unibo.pixArt.view.animation;

import it.unibo.pixArt.controller.animation.AnimationController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AnimationView extends AbstractFXView {

    @FXML
    private ChoiceBox<String> dimensionChoice;

    @FXML
    private ChoiceBox<String> directionChoice;

    @FXML
    private ListView<ImageView> frameList;

    @FXML
    private ImageView imageContainer;

    @FXML
    private Label selectedFrame;

    @FXML
    private Label selectedFrameDuration;

    @FXML
    private TextField frameDurationField;

    @FXML
    private Button switchBtn;

    @FXML
    public void switchAnimation() {
        this.getAnimationController().setAnimationIsRunning();
        this.getAnimationController().setAnimationDirection(this.directionChoice.getValue());
        if(this.getAnimationController().getAnimationIsRunning()) {
            switchBtn.setGraphic(new ImageView(new Image("/image/stopBtn.png")));
        } else {
            switchBtn.setGraphic(new ImageView(new Image("/image/startBtn.png")));
        }
    }

    @FXML
    public void onWorkSpaceClick() {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
    }

    @FXML
    public void setDuration() {
        this.getAnimationController().setFrameDuration(Integer.parseInt(selectedFrame.getText()), Integer.parseInt(frameDurationField.getText()));
    }

    @Override
    public void init() {
        this.switchBtn.setGraphic(new ImageView(new Image("/image/startBtn.png")));
        this.dimensionChoice.getItems().addAll(this.getAnimationController().getListSizes());
        this.dimensionChoice.setValue(this.getAnimationController().getListSizes().get(0));
        this.directionChoice.getItems().addAll(this.getAnimationController().getListDirections().stream().map(e -> e.getName()).toList());
        this.directionChoice.setValue(this.getAnimationController().getListDirections().get(0).getName());
        this.imageContainer.setImage(new Image(this.getAnimationController().getHistoryFrames().get(0).getPath()));
        this.frameList.getItems().addAll(this.getAnimationController().getHistoryFrames().stream()
                                                                                         .map(e -> new Image(e.getPath()))
                                                                                         .map(i -> new ImageView(i)).toList());
        this.frameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {

            @Override
            public void changed(ObservableValue<? extends ImageView> observable, ImageView oldValue, ImageView newValue) {
                imageContainer.setImage(frameList.getSelectionModel().getSelectedItem().getImage());
                selectedFrame.setText(Integer.toString(frameList.getSelectionModel().getSelectedIndex()));
                selectedFrameDuration.setText(getAnimationController().getHistoryFrames().get(frameList.getSelectionModel().getSelectedIndex()).getAnimationDuration() + "ms");
            }
           
        });
    }

    public void displayImage(final String path) {
        imageContainer.setImage(new Image(path));    
    }

    private AnimationController getAnimationController() {
        return (AnimationController) this.getController();
    }

}
