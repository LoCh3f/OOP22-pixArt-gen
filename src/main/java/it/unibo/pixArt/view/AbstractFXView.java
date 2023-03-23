package it.unibo.pixArt.view;

import javafx.stage.Stage;

public abstract class AbstractFXView extends SimpleView implements JavaFXView {
    private Stage stage;

    @Override
    public void setStage(final Stage stage) {
        this.stage = stage;
    }

    @Override
    public Stage getStage() {
        return this.stage;
    }
}