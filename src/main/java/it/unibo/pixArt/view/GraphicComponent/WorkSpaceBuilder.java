package it.unibo.pixArt.view.GraphicComponent;


import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import static javafx.geometry.Pos.CENTER;


public class WorkSpaceBuilder {

    private final static Double Y_SIZE = 500.0;
    private final static Double X_SIZE = 500.0;

    public static GridPane grid(final int rows, final int coulumns) {
        final GridPane grid = new GridPane();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < coulumns; j++) {
                grid.add(gridButton(), i, j);
            }
        }
        grid.setMinSize(X_SIZE, Y_SIZE);
        grid.setGridLinesVisible(true);
        grid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        grid.setAlignment(CENTER);

        return grid;
    }

    public static Button gridButton() {
        final var b = new Button() {
            public void setBackground(final String color) {
                super.setStyle(color);
            }
        };


        return b;
    }

    public static BorderPane root(final int rows, final int columns) {
        final var root = new BorderPane();
        root.setCenter(grid(rows, columns));
        root.setRight(new ColorPicker());
        root.setMinSize(X_SIZE, Y_SIZE);
        root.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);


        return root;
    }
}