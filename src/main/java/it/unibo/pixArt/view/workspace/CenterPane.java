package it.unibo.pixArt.view.workspace;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CenterPane {

    private final GridPane matrix = new GridPane();


    private CenterPane(final int rows,
                       final int columns,
                       final ObservableValue<? extends Number> rootW,
                       final ObservableValue<? extends Number> rootH,
                       final boolean lineVisible,
                       final EventHandler<ActionEvent> e) {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final var b = new Button();
                b.setOnAction(e);
                b.prefHeightProperty().bind(this.matrix.heightProperty());
                b.prefWidthProperty().bind(this.matrix.widthProperty());
                this.matrix.add(b, j, i);
            }

        }
        this.matrix.setGridLinesVisible(lineVisible);
        this.matrix.prefWidthProperty().bind(rootW);
        this.matrix.prefHeightProperty().bind(rootH);

    }

    public static class GridPaneBuilder {

        private boolean lineVisible;

        private ObservableValue<? extends Number> rootHProperty;

        private ObservableValue<? extends Number> rootWProperty;

        private int rows;

        private int columns;

        private EventHandler<ActionEvent> e;

        public GridPaneBuilder() {
            this.columns = 0;
            this.rows = 0;
            this.rootHProperty = null;
            this.rootWProperty = null;
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


        public GridPaneBuilder setHeight(final ObservableValue<? extends Number> rootH) {
            this.rootHProperty = rootH;
            return this;
        }

        public GridPaneBuilder setWidth(final ObservableValue<? extends Number> rootW) {
            this.rootWProperty = rootW;
            return this;
        }

        public GridPaneBuilder setAction(final EventHandler<ActionEvent> e) {
            this.e = e;
            return this;
        }

        public CenterPane build() {

            return new CenterPane(this.rows, this.columns, this.rootWProperty, this.rootHProperty, this.lineVisible, this.e);
        }

    }

    public GridPane get() {
        return this.matrix;
    }
}
