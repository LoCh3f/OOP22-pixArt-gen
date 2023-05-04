package it.unibo.pixArt.controller.workspace;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

import java.util.Set;

public interface WorkSpaceController extends Controller {

    /**
     * Method to set the tool that is being currently used.
     *
     * @param toolName the new tool's name
     */
    public void selectTool(final String toolName, final Color color, final int toolSize);

    /**
     * Method to select the current frame when clicked on the relative historyframe within the view.
     *
     * @param index the frame's index.
     */
    public void setCurrentFrame(final int index);

   /**Method to color the pixels of the grid.TO BE DONE.
    * 
    */
    public void colorGrid(final Pixel p);

    /**
     *Method to get the previous state of the frame.

     * @return A set of pixels representing the previous state of the frame.
     */
    public Set<Pixel> getPreviousState();

    /**
     * Method to create and add a new frame in the list of frames.
     */
    public void addNewFrame();

    
    /*TO BE DONE:
     * 2) Method to save the file.
     * 4) Method to delete current frame.
     */

}