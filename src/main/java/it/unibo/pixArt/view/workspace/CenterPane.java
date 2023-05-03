package it.unibo.pixArt.view.workspace;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CenterPane {

    private final GridPane matrix = new GridPane();

    private static final Double MAX_SIZE = 800.0;
    public static final String FX_BORDER_COLOR = "-fx-border-color:black";


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
                b.setPrefSize(30.0, 30.0);
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
