package it.unibo.pixArt.view.workspace;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

/* This is not a "Builder"!!  */


public class WorkSpaceRoot implements RootFactory {

    private final static Double SIZE = 500.0;

    private final static Double MAX_SIZE = 800.0;

    private final static Double MIN_SIZE = 300.0;

    @Override
    public GridPane createGrid(final int rows, final int columns) {
        final GridPane grid = new GridPane();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                final var b = createButton();
                b.prefHeightProperty().bind(grid.heightProperty());
                b.prefWidthProperty().bind(grid.widthProperty());
                grid.add(b, i, j);
            }
        }
        grid.setMinSize(250, 250);
        grid.setGridLinesVisible(true);
        return grid;
    }

    @Override
    public Button createButton() {
        final var b = new Button() {
            public void setBackground(final String color) {
                super.setStyle(color);
            }
        };
        b.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
        b.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return b;
    }

    @Override
    public Parent root(final int rows, final int columns) {
        final var root = new BorderPane();
        final var center = createGrid(rows, columns);
        final var right = new ColorPicker();
        final var bottom = new ScrollPane();
        final var left = new ButtonBar();
        final var top = new MenuBar();


        root.setPadding(new Insets(5));
        root.setStyle("-fx-background-color:pink");
        root.setTop(top);
        root.setRight(right);
        root.setCenter(center);
        root.setBottom(bottom);
        root.setLeft(left);


        center.setAlignment(Pos.CENTER);
        center.prefHeightProperty().bind(root.heightProperty().subtract(top.heightProperty().add(bottom.heightProperty())));
        center.prefWidthProperty().bind(center.heightProperty());
        center.setMinSize(MIN_SIZE, MIN_SIZE);
        center.setMaxSize(MAX_SIZE, MAX_SIZE);


        right.setPrefWidth(100);
        right.prefHeightProperty().bind(root.heightProperty().subtract(bottom.heightProperty().add(top.heightProperty())));
        right.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);

        left.setPrefWidth(100);
        left.prefHeightProperty().bind(root.heightProperty().subtract(bottom.heightProperty().add(top.heightProperty())));
        left.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);

        top.setPrefHeight(40);
        top.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
        top.prefWidthProperty().bind(root.widthProperty());

        bottom.setPrefHeight(50);
        bottom.setMinSize(Double.MIN_VALUE, Double.MIN_VALUE);
        bottom.prefWidthProperty().bind(root.widthProperty());

        return root;
    }


}