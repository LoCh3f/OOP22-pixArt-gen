package it.unibo.pixArt.controller.workspace;

import java.util.HashSet;
import java.util.Set;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import javafx.scene.paint.Color;

public class WorkSpaceControllerImpl extends SimpleController implements WorkSpaceController {
    private PixelGrid currentframe;
    private final ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractTool tool;

    public WorkSpaceControllerImpl() {
        //this.currentframe = getModel().getProject().getAllFrames().get(0);
        //Default tool?
    }    

    @Override
    public void selectTool(final String toolName, final Color color, final int toolSize) {
        this.tool = this.toolFactory.createTool(toolName, color, toolSize);
    }

    @Override
    public void colorGrid(final Pixel p) {
        final Set<Pixel> result = tool.updateGrid(p, this.currentframe.getPixels());
        this.currentframe.setPixel(result);
       //this.view.updateView(result); METHOD TO UPDATE VIEW.
    }

    @Override
    public void setCurrentFrame(final int index) {
        this.currentframe = this.getModel().getProject().getAllFrames().get(index);
        //this.view.updateView(this.currentframe.getState()) Need all the pixels in the currentframe.
    }

    @Override
    public Set<Pixel> getPreviousState() {
        return this.currentframe.getMemento().getState();//I shouldn't be able to access the memento. Should be changed.
    }

    @Override
    public void addNewFrame() {
        this.getModel().getProject().addNewFrame();
        setCurrentFrame(this.getModel().getProject().getAllFrames().size() - 1);
        //this.view.updateView(result); METHOD TO UPDATE VIEW.
    }
    
      /*TO BE DONE:
     * Method to save the file.
     * Method to delete current frame.
     * Method to get all history frames and send them in the view.
     */
    
}
