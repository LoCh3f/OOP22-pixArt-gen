package it.unibo.pixArt.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import static it.unibo.pixArt.utilities.FXStyleVariable.FX_BORDER_COLOR;

public class CenterPane {

    private final GridPane matrix = new GridPane();

    private static final Double MAX_SIZE = 900.0;


    private CenterPane(final int rows,
                       final int columns,
                       final boolean lineVisible,
                       final EventHandler<ActionEvent> e) {

        this.matrix.setGridLinesVisible(lineVisible);
        this.matrix.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
        this.matrix.setMaxSize(MAX_SIZE, MAX_SIZE);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final var b = new Button();
                b.setOnAction(e);
                b.setStyle(FX_BORDER_COLOR);
                b.prefWidthProperty().bind(matrix.heightProperty().divide(rows));
                b.prefHeightProperty().bind(matrix.heightProperty().divide(columns));
                b.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
                this.matrix.add(b, j, i);
            }
        }
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

        public CenterPane build() {

            return new CenterPane(this.rows, this.columns, this.lineVisible, this.e);
        }

    }

    public GridPane get() {
        return this.matrix;
    }
}
