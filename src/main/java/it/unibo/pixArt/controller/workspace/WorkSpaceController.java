package it.unibo.pixArt.controller.workspace;

import java.util.Set;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.workspace.WorkSpaceModel;
import it.unibo.pixArt.view.impl.WorkSpace;
import javafx.scene.paint.Color;

public interface WorkSpaceController extends Controller {
   /**Method to set the color that is being currently used.
    * 
    * @param color the new color.
    */
   public void selectCurrentColor(final Color newColor);

   /**Method to set the tool that is being currently used.
    * 
    * @param toolName the new tool's name
    */
   public void selectTool(final String toolName);

   /**Method to select the current frame when clicked on the relative historyframe within the view.
    * 
    * @param index the frame's index.
    */
   public void setCurrentFrame(final int index);

   /**Method to color the pixels of the grid.TO BE DONE.
    * 
    */
   public void colorGrid(final int x, final int y);

   public Set<Pixel> getFrameState();
   /*TO BE DONE:
    * 1) Method to get previous state.
    * 2) Method to save the file.
    * 3) Method to add new frame.
    * 4) Method to delete current frame.
    */

}