package it.unibo.pixArt.view.workspace;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public interface Logic {

    boolean isDrawing();

    void changeState();

    boolean isValid(final int index, final ObservableList<Node> buttons, final int father);

    String getImagePath();
}
