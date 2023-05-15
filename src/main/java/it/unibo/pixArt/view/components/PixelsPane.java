package it.unibo.pixArt.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.FX_BORDER_COLOR;
import static it.unibo.pixArt.utilities.variables.FXViewVariables.FX_BORDER_WIDTH;

public class PixelsPane extends GridPane {


    private static final Double MAX_SIZE = 1080.0;


    private PixelsPane(final int rows,
                       final int columns,
                       final boolean lineVisible,
                       final EventHandler<ActionEvent> e) {
        super();
        this.setGridLinesVisible(lineVisible);
        this.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
        this.setMaxSize(MAX_SIZE, MAX_SIZE);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final var b = new Button();
                b.setOnAction(e);
                b.setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
                b.prefWidthProperty().bind(this.widthProperty().divide(columns));
                b.prefHeightProperty().bind(this.heightProperty().divide(rows));
                b.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
                this.add(b, j, i);
            }
        }
        setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
    }

    public static class GridPaneBuilder {

        private boolean lineVisible;


        private int rows;

        private int columns;

        private EventHandler<ActionEvent> e;

        public GridPaneBuilder() {
            this.columns = 0;
            this.rows = 0;
            this.lineVisible = false;

        }

        public GridPaneBuilder setRows(final int rows) {
            this.rows = rows;
            return this;
        }

        public GridPaneBuilder setColumns(final int columns) {
            this.columns = columns;
            return this;
        }

        public GridPaneBuilder setGridLinesVisible(final boolean value) {
            this.lineVisible = value;
            return this;
        }

        public GridPaneBuilder setAction(final EventHandler<ActionEvent> e) {
            this.e = e;
            return this;
        }

        public PixelsPane build() {

            return new PixelsPane(this.rows, this.columns, this.lineVisible, this.e);
        }

    }


}
