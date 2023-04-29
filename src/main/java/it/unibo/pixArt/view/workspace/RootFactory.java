package it.unibo.pixArt.view.workspace;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public interface RootFactory {

    Parent root(final int rows, final int columns);

    Button createButton();

    GridPane createGrid(final int rows, final int columns);


}
