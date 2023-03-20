package it.unibo.pixArt.view;

import it.unibo.pixArt.controller.Controller;

public interface View {

    Controller getController();

    void setController(final Controller controller);
}
