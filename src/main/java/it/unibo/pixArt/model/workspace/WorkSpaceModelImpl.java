package it.unibo.pixArt.model.workspace;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import javafx.scene.paint.Color;

public class WorkSpaceModelImpl extends ModelImpl implements WorkSpaceModel {
    private PixelGrid currentframe;//To controller
    private ToolFactory toolFactory;//To controller
    private AbstractTool tool;//To controller
    private Color selectedColor;//From view
    private int toolSize;//From view
    private Boolean inUse = false;//View or controller?

    public WorkSpaceModelImpl(final Project project) {
        super(null, null, project);
        System.out.println(this.getProject().getAllFrames().get(0).getColumns());
        System.out.println(this.getProject().getAllFrames().get(0).getColumns());
    }

    @Override
    public void setTool(String toolName) {
        this.toolFactory = new ToolFactoryImpl();
        this.tool = toolFactory.createTool(toolName, this.selectedColor, this.toolSize);
    }

    @Override
    public void setColor(Color newColor) {
        this.selectedColor = newColor;
    }

    @Override
    public void setInUse() {
        this.inUse = !inUse;
    }

    @Override
    public void setCurrentFrame(int index) {
        this.currentframe = getProject().getAllFrames().get(index);
    }

    @Override
    public PixelGrid getFrameState() {
        return this.currentframe;
    }

    @Override
    public void colorGrid(Pixel pixel) {
        this.currentframe.setPixel(this.tool.updateGrid(pixel, this.currentframe.getPixels()));
    }

    /*
     * 1) Method to get previous state
     * 2) Method to save the file
     * 3) Method to add new frame
     * 4) Method to delete current frame
     */


}
