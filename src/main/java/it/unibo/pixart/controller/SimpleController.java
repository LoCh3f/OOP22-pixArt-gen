package  it.unibo.pixart.controller;

import it.unibo.pixart.model.Model;
import it.unibo.pixart.view.View;

/**
 * Implementation of a basic controller.
 */
public class SimpleController implements Controller {

    private Model model;

    private View view;

    @Override
    public final void setModel(final Model model) {
        this.model = model;
    }

    @Override
    public final void setView(final View view) {
        this.view = view;
    }

    @Override
    public final Model getModel() {
        return this.model;
    }

    @Override
    public final View getView() {
        return this.view;
    }
}