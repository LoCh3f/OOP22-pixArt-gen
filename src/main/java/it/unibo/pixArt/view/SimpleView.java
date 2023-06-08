package it.unibo.pixArt.view;

import it.unibo.pixArt.controller.Controller;
/**
 * doc.
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