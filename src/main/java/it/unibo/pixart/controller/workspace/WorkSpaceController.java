package it.unibo.pixart.controller.workspace;

import javafx.scene.paint.Color;

import java.util.List;
import java.util.Set;

import it.unibo.pixart.controller.Controller;
import it.unibo.pixart.model.historyframe.HistoryFrame;
import it.unibo.pixart.model.pixel.Pixel;
/**
 * interface for WorkSpaceController.
 */
public interface WorkSpaceController extends Controller {

    /**
     * Method to set the tool that is being currently used.
     *
     * @param toolName the new tool's name
     * @param color
     * @param toolSize
     */
    void selectTool(String toolName, Color color, int toolSize);

    /**
     * Method to select the current frame when clicked on the relative historyframe within the view.
     *
     * @param index the frame's index.
     */
    void setCurrentFrame(int index);

   /**
    * Method to color the pixels of the grid.TO BE DONE.
    * @param color
    * @param x
    * @param y
    */
    void colorGrid(int x, int y, Color color);

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
    boolean isDrawing();

    /**
     * @param scale The scale size.
     */
    void saveProject(int scale);

    /** 
     * Method to get the current frame.
     * @return the set of pixels that make up the curent frame.
     */
    Set<Pixel> getCurrentFrame();

    /**
     * Method to delete current frame. 
     */
    void deleteCurrentFrame();

    /**
     * Method to set the first frame.
     */
    void setFirstFrame();

    /**
    * Reset the current frame.
    */
    void resetCurrentFrame();

}
