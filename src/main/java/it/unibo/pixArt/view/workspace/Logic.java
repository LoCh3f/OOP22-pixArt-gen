package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.Pixel;

import java.util.Collection;

public interface Logic {

    boolean isDrawing();

    void changeState();

    String getImagePath();

    String test(final Collection<Pixel> userGrid);

}
