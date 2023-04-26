package  it.unibo.pixArt.controller;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.View;

public abstract class SimpleController implements Controller {

    private Model model;

    private View view;

    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void setView(View view) {
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