package it.unibo.pixart.view;

import it.unibo.pixart.controller.Controller;
/**
 * Interface for a basic view.
 */
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
