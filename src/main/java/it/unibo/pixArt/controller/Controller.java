package  it.unibo.pixArt.controller;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.View;

public interface Controller  {
    /**
     *
     * @return model of this controller
     */
    Model getModel();

    /**
     * @param model to set for this controller
     */
    void setModel(final Model model);

    /**
     * @return the view of this controller
     */
    View getView();

    /**
     * @param view to set for this controller
     */
    void  setView(final View view);
}
