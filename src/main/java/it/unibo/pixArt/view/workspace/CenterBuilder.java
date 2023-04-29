package it.unibo.pixArt.view.workspace;

import com.sun.jdi.Value;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class CenterBuilder {

    private final GridPane matrix;

    private CenterBuilder(final int rows,
                          final int columns,
                          final ObservableValue rootW,
                          final ObservableValue rootH,
                          final boolean lineVisible) {
        this.matrix = new GridPane();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final var b = new Button();
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

        private ObservableValue rootHProperty;

        private ObservableValue rootWProperty;

        private int rows;

        private int columns;

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


        public GridPaneBuilder setHeight(final ObservableValue<? extends Value> rootH) {
            this.rootHProperty = rootH;
            return this;
        }

        public GridPaneBuilder setWidth(final ObservableValue<? extends Value> rootW) {
            this.rootWProperty = rootW;
            return this;
        }

        public CenterBuilder build() {

            return new CenterBuilder(this.rows, this.columns, this.rootWProperty, this.rootHProperty, this.lineVisible);
        }

    }

    public GridPane get() {
        return this.matrix;
    }
}
