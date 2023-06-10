package it.unibo.pixart.view.animation;

import java.util.stream.Collectors;

import it.unibo.pixart.controller.animation.AnimationController;
import it.unibo.pixart.view.AbstractFXView;
import it.unibo.pixart.view.pages.Pages;
import it.unibo.pixart.view.pages.SceneManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * view for animating the images of a project.
 */
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

    /**
     * Method to start/stop animation.
     */
    @FXML
    public void switchAnimation() {
        this.getAnimationController().setAnimationIsRunning();
        if (this.getAnimationController().isAnimationIsRunning()) {
            switchBtn.setGraphic(new ImageView(new Image(STOP)));
        } else {
            switchBtn.setGraphic(new ImageView(new Image(START)));
        }
    }

    /**
     * Method to go back to WorkSPace.
     */
    @FXML
    public void onWorkSpaceClick() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, this.getController().getModel());
    }

    /**
     * Method to exit to Home.
     */
    @FXML
    public void onExitClick() {
        SceneManager.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    /**
     * Method to save project.
     */
    @FXML
    public void onSaveClick() {
        getAnimationController().saveProject();
        SceneManager.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    /**
     * Method to set the aniamtion delay of a HistoryFrame.
     */
    @FXML
    public void setDuration() {
        this.getAnimationController().setFrameDuration(Integer.parseInt(selectedFrame.getText()),
        Integer.parseInt(frameDurationField.getText()));
    }

    @Override
    public void init() {
        this.frameDurationField.setDisable(true);
        this.setDurationBtn.setDisable(true);
        this.switchBtn.setGraphic(new ImageView(new Image(START)));
        this.imageContainer.setImage(new Image("file:" + this.getAnimationController().getHistoryFrames().get(0).getPath()));
        this.selectedFrame.setText(Integer.toString(0));
        this.selectedFrameDuration.setText(getAnimationController().getHistoryFrames().get(0).getAnimationDuration() + "ms");
        this.frameList.getItems().addAll(this.getAnimationController().getHistoryFrames()
                                                                      .stream()
                                                                      .map(e -> new Image("file:" + e.getPath()))
                                                                      .map(i -> new ImageView(i)).collect(Collectors.toList()));

        this.frameList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ImageView>() {

        @Override
        public void changed(final ObservableValue<? extends ImageView> observable, 
                                final ImageView oldValue, final ImageView newValue) {
                imageContainer.setImage(frameList.getSelectionModel().getSelectedItem().getImage());
                selectedFrame.setText(Integer.toString(frameList.getSelectionModel().getSelectedIndex()));
                selectedFrameDuration.setText(getAnimationController().getHistoryFrames()
                .get(frameList.getSelectionModel().getSelectedIndex())
                .getAnimationDuration() + "ms");

                frameDurationField.setDisable(false);
                setDurationBtn.setDisable(false);
            }
        });
    }

    /**
     * Maethod to display an image.
     * @param path
     */
    public void displayImage(final String path) {
        imageContainer.setImage(new Image("file:" + path)); 
    }

    private AnimationController getAnimationController() {
        return (AnimationController) this.getController();
    }

}
