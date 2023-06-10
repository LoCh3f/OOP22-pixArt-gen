package it.unibo.pixart.view;

import it.unibo.pixart.controller.Controller;
/**
 * Implementation of a basic view.
 */
public class SimpleView implements View {

    private Controller controller;


    /**
     * {@inheritDoc}
     */
    @Override
    public Controller getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }
}
