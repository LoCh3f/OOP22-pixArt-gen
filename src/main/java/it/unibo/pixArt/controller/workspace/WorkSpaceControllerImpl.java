package it.unibo.pixArt.controller.workspace;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.historyframe.HistoryFrameImpl;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import it.unibo.pixArt.model.project.FileTypes;
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
    private int frameIndex;

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
        ImagePrinter.getInstance().printOneFrame(currentframe, getModel().getProject().getPath() + File.separatorChar + getModel().getProject().getName() + frameIndex + getModel().getProject().getFileType(), FileTypes.PNG);
        this.getHistoryFrames().get(frameIndex).setPath(getModel().getProject().getPath() + File.separatorChar + getModel().getProject().getName() + frameIndex + getModel().getProject().getFileType());
        this.frameIndex = index;
        this.currentframe = getModel().getProject().getAllFrames().get(frameIndex);
    }

    @Override
    public void setFirstFrame() {
        this.currentframe = this.getModel().getProject().getAllFrames().get(0);
        this.frameIndex = 0;
    }

    @Override
    public Set<Pixel> getPreviousState() {
        currentframe.revert();
        return currentframe.getPixels();
    }

    @Override
    public Set<Pixel> addNewFrame() {
        ImagePrinter.getInstance().printOneFrame(currentframe, getModel().getProject().getPath() + File.separatorChar + getModel().getProject().getName() + frameIndex + getModel().getProject().getFileType(), FileTypes.PNG);
        this.getHistoryFrames().get(getModel().getProject().getAllFrames().indexOf(currentframe)).setPath(getModel().getProject().getPath() + File.separatorChar + getModel().getProject().getName() + frameIndex + getModel().getProject().getFileType());
        this.getModel().getProject().addNewFrame();
        this.getModel().getProject().getAllHistoryFrames().add(new HistoryFrameImpl());
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
       try {
        FileHandler.getInstance().fromProjectToJson(this.getModel().getProject());
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    }

    @Override
    public void deleteCurrentFrame() {
        this.getHistoryFrames().remove(frameIndex);
        this.getModel().getProject().getAllFrames().remove(currentframe);
        if(this.getModel().getProject().getAllFrames().size() > 1) {
            //Rimuovi immagine
            
            this.currentframe = getModel().getProject().getAllFrames().get(frameIndex - 1);
            this.getWorkSpaceView().updateView(getCurrentFrame());
        }else {
            this.currentframe = null;
        }
    }

    private WorkSpace getWorkSpaceView() {
        return (WorkSpace) getView();
    }
}
