package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.view.AbstractFXView;
import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class WorkSpace extends AbstractFXView {

    @FXML
    public MenuBar menubar;
    @FXML
    private BorderPane root;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ScrollPane frames;
    private GridPane center;

    private static final String BACKGROUND_COLOR = "-fx-background-color:pink";

    private static final String FX_BACKGROUND_COLOR = "-fx-background-color:";


    @Override
    public void init() {
        root.setStyle(BACKGROUND_COLOR);
        final var rows = this.getController().getModel().getProject().getAllFrames().get(0).getRows();
        final var columns = this.getController().getModel().getProject().getAllFrames().get(0).getColumns();
        this.center = new CenterPane.GridPaneBuilder()
                .setColumns(columns).setRows(rows)
                .setGridLinesVisible(true)
                .setHeight(root.heightProperty().subtract(frames.heightProperty().add(menubar.heightProperty())))
                .setHeight(root.widthProperty().subtract(colorPicker.widthProperty())).build().get();
        this.root.setCenter(center);

    }
}
