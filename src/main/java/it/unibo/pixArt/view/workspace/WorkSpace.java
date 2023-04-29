package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.view.AbstractFXView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class WorkSpace extends AbstractFXView {

    private GridPane center;


    @Override
    public void init() {
        final var root = (BorderPane) this.getStage().getScene().getRoot();
        this.center = new GridPane();

    }
}
