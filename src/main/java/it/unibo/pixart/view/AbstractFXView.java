package it.unibo.pixart.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import javafx.stage.Stage;

/**
 * Abstract class that extends a SimpleView.
 */
public abstract class AbstractFXView extends SimpleView implements JavaFXView {
    private Stage stage;

    @SuppressFBWarnings
    @Override
    public final void setStage(final Stage stage) {
        this.stage = stage;
    }

    @SuppressFBWarnings
    @Override
    public final Stage getStage() {
        return this.stage;
    }
}
