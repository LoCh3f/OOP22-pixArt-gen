package it.unibo.pixArt.view.workspace;

public interface Logic {

    Boolean isDrawing();

    void changeState();

    void setState(final boolean isDrawing);
}
