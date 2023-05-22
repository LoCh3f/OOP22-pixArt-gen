package it.unibo.pixArt.controller.workspace;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.historyframe.HistoryFrameImpl;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.ToolEnum;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import it.unibo.pixArt.utilities.FileHandler;
import it.unibo.pixArt.utilities.ImagePrinter;
import it.unibo.pixArt.view.workspace.WorkSpace;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorkSpaceControllerImpl extends SimpleController implements WorkSpaceController {
    private Matrix currentframe;
    private final ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractTool tool;
    private boolean isDrawing;

    public WorkSpaceControllerImpl() {
    }

    @Override
    public void selectTool(final String toolName, final Color color, final int toolSize) {
        this.tool = this.toolFactory.createTool(toolName, color, toolSize);
    }

    @Override
    public void colorGrid(final int x, final int y, final Color color) {
        final Pixel p = this.currentframe.getPixels().stream().filter(e -> e.comparePixel(new PixelBuilder.PxlBuilder().setX(x).setY(y).setColor(color).build())).findAny().get();
        final Set<Pixel> result = tool.updateGrid(p, this.currentframe.getPixels());
        this.currentframe.getMemento().setState(currentframe.getPixels());
        this.currentframe.setPixel(result);
        this.getWorkSpaceView().updateView(result);
    }

    @Override
    public void setCurrentFrame(final int index) {
        //elimina immagine e ricreala.(ImagePrinter.)
        this.currentframe = this.getModel().getProject().getAllFrames().get(index);
    }

    @Override
    public Set<Pixel> getPreviousState() {
        currentframe.revert();
        return currentframe.getPixels();
    }

    //DA FINIREs
    @Override
    public Set<Pixel> addNewFrame() {
        this.getModel().getProject().addNewFrame();
        this.getModel().getProject().getAllHistoryFrames().add(new HistoryFrameImpl());
        /*this.getModel().getProject().getAllHistoryFrames().add(new HistoryFrameImpl(getModel().getUser().getPassword() + getModel().getProject().getName() 
        + getModel().getProject().getAllHistoryFrames().size()));*/
        setCurrentFrame(this.getModel().getProject().getAllFrames().size() - 1);
        return this.currentframe.getPixels();
    }

    @Override
    public List<HistoryFrame> getHistoryFrames() {
        return this.getModel().getProject().getAllHistoryFrames();
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

    @Override
    public Set<Pixel> getCurrentFrame() {
        return this.currentframe.getPixels();
    }

    @Override
    public void saveProject() {
        this.currentframe.getMemento().emptyStack();
        ImagePrinter.getInstance().printAllFrames(this.getModel().getProject());
    }

    @Override
    public void deleteCurrentFrame() {
        //da migliorare
        final int frameIndex = this.getModel().getProject().getAllFrames().indexOf(this.currentframe);
        //Rimuovi immagine
        this.getHistoryFrames().remove(frameIndex);
        this.getModel().getProject().getAllFrames().remove(currentframe);
        this.setCurrentFrame(frameIndex - 1);
        this.getWorkSpaceView().updateView(getCurrentFrame());
    }

    private WorkSpace getWorkSpaceView() {
        return (WorkSpace) getView();
    }
}
