package it.unibo.pixArt.view.animation;

import java.util.stream.Collectors;

import it.unibo.pixArt.controller.animation.AnimationController;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.SceneManager;
import it.unibo.pixArt.view.pages.Pages;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public final class AnimationView extends AbstractFXView {

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
    private Button setDurationBtn;

    private static final String START = "/image/startBtn.png";
    private static final String STOP = "/image/stopBtn.png";

    @FXML
    public void switchAnimation() {
        this.getAnimationController().setAnimationIsRunning();
        if (this.getAnimationController().getAnimationIsRunning()) {
            switchBtn.setGraphic(new ImageView(new Image(STOP)));
        } else {
            switchBtn.setGraphic(new ImageView(new Image(START)));
        }
    }

    @FXML
    public void onWorkSpaceClick() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
    }

    @FXML
    public void onExitClick() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    @FXML
    public void onSaveClick() {
        getAnimationController().saveProject();
        SceneManager.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    @FXML
    public void setDuration() {
        this.getAnimationController().setFrameDuration(Integer.parseInt(selectedFrame.getText()), Integer.parseInt(frameDurationField.getText()));
    }

    @Override
    public void init() {
        this.frameDurationField.setDisable(true);
        this.setDurationBtn.setDisable(true);
        this.switchBtn.setGraphic(new ImageView(new Image(START)));
        this.imageContainer.setImage(new Image("file:" + this.getAnimationController().getHistoryFrames().get(0).getPath()));
        this.selectedFrame.setText(Integer.toString(0));
        this.selectedFrameDuration.setText(getAnimationController().getHistoryFrames().get(0).getAnimationDuration() + "ms");
        this.frameList.getItems().addAll(this.getAnimationController().getHistoryFrames().stream()
                                                                                         .map(e -> new Image("file:" + e.getPath()))
                                                                                         .map(i -> new ImageView(i)).collect(Collectors.toList()));

        this.frameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {

            @Override
            public void changed(final ObservableValue<? extends ImageView> observable, final ImageView oldValue, final ImageView newValue) {
                imageContainer.setImage(frameList.getSelectionModel().getSelectedItem().getImage());
                selectedFrame.setText(Integer.toString(frameList.getSelectionModel().getSelectedIndex()));
                selectedFrameDuration.setText(getAnimationController().getHistoryFrames().get(frameList.getSelectionModel().getSelectedIndex()).getAnimationDuration() + "ms");
                frameDurationField.setDisable(false);
                setDurationBtn.setDisable(false);
            }
        });
    }

    public void displayImage(final String path) {
        imageContainer.setImage(new Image("file:" + path)); 
    }

    private AnimationController getAnimationController() {
        return (AnimationController) this.getController();
    }

}
