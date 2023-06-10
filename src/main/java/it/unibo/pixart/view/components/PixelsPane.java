package it.unibo.pixart.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import static it.unibo.pixart.utilities.variables.FXViewVariables.*;

/**
 * usefull class for create a grid of button.
 */
public final class PixelsPane extends GridPane {


    private static final Double MAX_SIZE = 1080.0;


    /**
     * @param rows        number of rows
     * @param columns     number of columns
     * @param lineVisible boolean for set the grid lines visible
     * @param e           action to set for each the button
     */
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
                b.setStyle(FX_BACKGROUND_COLOR_START + "white" + ";" + FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
                b.prefWidthProperty().bind(this.widthProperty().divide(columns));
                b.prefHeightProperty().bind(this.heightProperty().divide(rows));
                b.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
                this.add(b, j, i);
            }
        }
        setStyle(FX_BORDER_COLOR + ";" + FX_BORDER_WIDTH);
    }

    /**
     * Builder for build a grid of button.
     */
    public static final class GridPaneBuilder {

        private boolean lineVisible;


        private int rows;

        private int columns;

        private EventHandler<ActionEvent> e;

        /**
         * constructor.
         */
        public GridPaneBuilder() {
            this.columns = 0;
            this.rows = 0;
            this.lineVisible = false;

        }

        /**
         * @param rows number of rows
         * @return the builder
         */
        public GridPaneBuilder setRows(final int rows) {
            this.rows = rows;
            return this;
        }

        /**
         * @param columns number of columns
         * @return the builder
         */
        public GridPaneBuilder setColumns(final int columns) {
            this.columns = columns;
            return this;
        }

        /**
         * @param value boolean for set the grid lines visible
         * @return the builder
         */
        public GridPaneBuilder setGridLinesVisible(final boolean value) {
            this.lineVisible = value;
            return this;
        }

        /**
         * @param e action to set for each the button
         * @return the builder
         */
        public GridPaneBuilder setAction(final EventHandler<ActionEvent> e) {
            this.e = e;
            return this;
        }

        /**
         * @return the gridPane of button
         */
        public PixelsPane build() {

            return new PixelsPane(this.rows, this.columns, this.lineVisible, this.e);
        }

    }


}
