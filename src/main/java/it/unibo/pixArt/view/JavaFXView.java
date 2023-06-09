package it.unibo.pixArt.view;

import javafx.stage.Stage;

/**
 * Interface for a basic JavaFXView.
 */
public interface JavaFXView extends View {

    /**
     * @return the stage of this view
     */
    Stage getStage();

    /**
     * @param stage to set for this view
     */
    void setStage(Stage stage);

    /**
     * Method to execute some tasks after the stage switch.
     */
    void init();
}