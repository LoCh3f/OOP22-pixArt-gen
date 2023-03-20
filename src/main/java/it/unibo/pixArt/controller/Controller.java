package  it.unibo.pixArt.controller;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.View;

public interface Controller  {

    Model getModel();

    void setModel(final Model model);

    View getView();

    void  setView(final View view);
}