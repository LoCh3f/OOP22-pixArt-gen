package it.unibo.pixArt.view;

import javafx.stage.Stage;

public interface JavaFXView extends View {

    /**
     * @return the stage of this view
     */
    Stage getStage();

    /**
     * @param stage to set for this view
     */
    void setStage(final Stage stage);

    void init();
}