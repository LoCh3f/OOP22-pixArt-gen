package it.unibo.pixart.view;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.pixart.controller.Controller;

/**
 * Implementation of a basic view.
 */
public class SimpleView implements View {

    private Controller controller;


    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings
    @Override
    public Controller getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings
    @Override
    public void setController(final Controller controller) {
        this.controller = controller;
    }
}
