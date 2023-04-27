package it.unibo.pixArt.model.workspace;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.tool.DrawingTool;
import it.unibo.pixArt.model.tool.FillTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryProvider;
import javafx.scene.paint.Color;

public class WorkSpaceModelImpl<T> extends ModelImpl implements WorkSpaceModel{
    private PixelGrid currentframe;
    private ToolFactory<T> toolFactory;
    private T tool;
    private Color selectedColor;
    private Boolean inUse = false;

    public WorkSpaceModelImpl(final Project project){
        super(null, null, project);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setTool(String toolName, final boolean fill) {
        this.toolFactory = ToolFactoryProvider.getFactory(fill);
        this.tool = (T) toolFactory.createTool(toolName, selectedColor);
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
    public Set<Pixel> getFrameState() {
        return Set.copyOf(this.currentframe.getPixels());
    }

    @Override
    public void colorGrid(Pixel pixel) {
        
        //serve metodo getSize nel frame che ritorni la dimensione del tool da utilizzare
        /*if (this.tool instanceof DrawingTool) {
            this.currentframe.update(((DrawingTool) this.tool).updatePixel(pixel, size, this.currentframe));
        } else {
            this.currentframe.update(((FillTool) this.tool).updatePixel(pixel, getFrameMap(this.currentframe.getPixels())));
        }*/
    }

    private Map<Pixel, Pixel> getFrameMap(Set<Pixel> frame) {
        return frame.stream().collect(Collectors.toMap(Function.identity(), Function.identity()));
    }

    /*
     * 1) Method to get previous state
     * 2) Method to save the file
     * 3) Method to add new frame
     * 4) Method to delete current frame
     */


}
