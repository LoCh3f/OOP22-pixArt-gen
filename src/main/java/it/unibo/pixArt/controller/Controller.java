package  it.unibo.pixArt.controller;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.view.View;

/**
 * interface for a generic Controller.
 */
public interface Controller  {
    /**
     *
     * @return model of this controller
     */
    Model getModel();

    /**
     * @param model to set for this controller
     */
    void setModel(Model model);

    /**
     * @return the view of this controller
     */
    View getView();

    /**
     * @param view to set for this controller
     */
    void  setView(View view);
}
