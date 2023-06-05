package it.unibo.pixArt.controller.workspace;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

import java.util.List;
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
    public void colorGrid(final int x, final int y, final Color color);

    /**
     *Method to get the previous state of the frame.

     * @return A set of pixels representing the previous state of the frame.
     */
    Set<Pixel> getPreviousState();

    /**
     * Method to create and add a new frame in the list of frames.
     */
    void addNewFrame();

    /**
     * @return list of all the historyframes of the project
     */
    List<HistoryFrame> getHistoryFrames();

    /**
     * @return List containing all the available tools.
     */
    List<String> getTools();

    /**
     * Method to set the isDrawing flag to true or false.
     */
    void setIsDrawing();

    /**Method to get the value of the flag isDrawing.
     * 
     * @return the boolean value of isDrawing.
     */
    boolean getIsDrawing();

    
    /**
     * @param scale The scale size
     */
    void saveProject(int scale);

    /** Method to get the current frame
     * 
     * @return the set of pixels that make up the curent frame.
     */
    Set<Pixel> getCurrentFrame();

    /**
     * Method to delete current frame. 
     */
    void deleteCurrentFrame();

    /*
     * Method to set the first frame.
     */
    void setFirstFrame();

}