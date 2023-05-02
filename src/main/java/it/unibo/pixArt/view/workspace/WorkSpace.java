package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.view.AbstractFXView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

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
        final var e = new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                final var button = (Button) event.getSource();
                button.setStyle(FX_BACKGROUND_COLOR + colorPicker.getValue().toString().replace("0x", "#"));
                getController().getModel().getProject().getAllFrames().get(0).getPixels().forEach(p -> {
                    if (p.comparePixel(new ImplPixel(GridPane.getColumnIndex(button), GridPane.getRowIndex(button)))) {
                        p.setColor(new Color(colorPicker.getValue().getRed(), colorPicker.getValue().getGreen(), colorPicker.getValue().getBlue(), colorPicker.getValue().getOpacity()));
                    }
                });
            }
        };

        final var rows = this.getController().getModel().getProject().getAllFrames().get(0).getRows();
        final var columns = this.getController().getModel().getProject().getAllFrames().get(0).getColumns();

        this.center = new CenterPane.GridPaneBuilder()
                .setColumns(columns).setRows(rows)
                .setGridLinesVisible(true)
                .setAction(e).build().get();
        center.alignmentProperty().set(Pos.CENTER);
        center.prefWidthProperty().bind(this.center.heightProperty());
        center.prefHeightProperty().bind(this.root.heightProperty().subtract(menubar.heightProperty().add(frames.heightProperty())));
        this.root.setCenter(this.center);


    }
}
