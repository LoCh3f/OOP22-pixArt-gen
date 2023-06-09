package  it.unibo.pixArt.controller;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.View;

/**
 * Implementation of a basic controller.
 */
public class SimpleController implements Controller {

    private Model model;

    private View view;

    @Override
    public void setModel(final Model model) {
        this.model = model;
    }

    @Override
    public void setView(final View view) {
        this.view = view;
    }

    @Override
    public Model getModel() {
        return this.model;
    }

    @Override
    public View getView() {
        return this.view;
    }
}