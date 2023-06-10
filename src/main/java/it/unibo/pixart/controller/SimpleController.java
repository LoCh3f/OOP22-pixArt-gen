package it.unibo.pixart.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.pixart.model.Model;
import it.unibo.pixart.view.View;

/**
 * Implementation of a basic controller.
 */
public class SimpleController implements Controller {

    private Model model;

    private View view;

    @SuppressFBWarnings
    @Override
    public final void setModel(final Model model) {
        this.model = model;
    }

    @SuppressFBWarnings
    @Override
    public final void setView(final View view) {
        this.view = view;
    }

    @SuppressFBWarnings
    @Override
    public final Model getModel() {
        return this.model;
    }

    @SuppressFBWarnings
    @Override
    public final View getView() {
        return this.view;
    }
}
