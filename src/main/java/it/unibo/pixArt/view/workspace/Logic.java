package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.Collection;

public interface Logic {

    boolean isDrawing();

    void changeState();

    boolean isValid(final int index, final ObservableList<Node> buttons, final int father);

    String getImagePath();

    String test(final Collection<Pixel> userGrid);

}
