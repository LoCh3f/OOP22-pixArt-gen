package it.unibo.pixArt.controller.workspace;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.ToolEnum;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import it.unibo.pixArt.utilities.ImagePrinter;
import it.unibo.pixArt.view.workspace.WorkSpace;
import javafx.scene.paint.Color;

public class WorkSpaceControllerImpl extends SimpleController implements WorkSpaceController {
    private PixelGrid currentframe; //= this.getModel().getProject().getAllFrames().get(0);
    private final ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractTool tool;
    private boolean isDrawing;

    public WorkSpaceControllerImpl() {
        //this.currentframe = getModel().getProject().getAllFrames().get(0);
        //Default tool?
    }    

    @Override
    public void selectTool(final String toolName, final Color color, final int toolSize) {
        this.tool = this.toolFactory.createTool(toolName, color, toolSize);
    }

    @Override
    public void colorGrid(final int x, final int y) {
        final Set<Pixel> result = tool.updateGrid(new ImplPixel(x, y), this.currentframe.getPixels());
        this.currentframe.setPixel(result);
        this.getWorkSpaceView().updateView(result); 
    }

    @Override
    public void setCurrentFrame(final int index) {
        this.currentframe = this.getModel().getProject().getAllFrames().get(index);
        //this.view.updateView(this.currentframe.getState()); Need all the pixels in the currentframe.
    }

    @Override
    public Set<Pixel> getPreviousState() {
        return this.currentframe.getMemento().getState();
    }

    @Override
    public void addNewFrame() {
        this.getModel().getProject().addNewFrame();
        setCurrentFrame(this.getModel().getProject().getAllFrames().size() - 1);
        //this.view.updateView(result); METHOD TO UPDATE VIEW.
    }

    @Override
    public List<HistoryFrame> getHistoryFrames() {//I need a method in the PixelGrid to return its HistoryFrame.
       // return this.getModel().getProject().getAllFrames().stream().map(e -> e.getHistoryFrame()).toList();
       return null;
    }

    @Override
    public List<String> getTools() {
        return Stream.of(ToolEnum.values()).map(e -> e.getName()).collect(Collectors.toList());
    }

    @Override
    public void setIsDrawing() {
        this.isDrawing = !this.isDrawing;
    }

    @Override
    public boolean getIsDrawing() {
        return this.isDrawing;
    }

    /*TO BE DONE:
    * Method to delete current frame.
    * Method to get all history frames and send them in the view.---ALMOST DONE.
    */
    
    @Override
    public void saveProject() {
        ImagePrinter.getInstance().printImage(this.getModel().getProject(), this.getModel().getUser());
    }
    
    private WorkSpace getWorkSpaceView() {
        return (WorkSpace) getView();
    }
}
/*Problemi:
 * 1)Bucket: al bucket passo un pixel nuovo, che di default è bianco.
 * 2)Lighten/Darken tool: stesso problema del bucket.
 * 3)Lagga troppo rispetto al drawTest.
 */
