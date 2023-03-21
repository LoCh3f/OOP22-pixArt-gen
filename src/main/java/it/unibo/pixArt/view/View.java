package it.unibo.pixArt.view;

import it.unibo.pixArt.controller.Controller;

public interface View {

    /**
     * @return controller of this view
     */
    Controller getController();

    /**
     * @param controller to set for this view
     */
    void setController(Controller controller);
}
