package it.unibo.pixart.controller.workspace;

import it.unibo.pixart.controller.SimpleController;
import it.unibo.pixart.model.grid.Matrix;
import it.unibo.pixart.model.grid.PixelMatrix;
import it.unibo.pixart.model.historyframe.HistoryFrame;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.pixel.PixelBuilder;
import it.unibo.pixart.model.tools.AbstractTool;
import it.unibo.pixart.model.tools.ToolEnum;
import it.unibo.pixart.model.tools.ToolFactory;
import it.unibo.pixart.model.tools.ToolFactoryImpl;
import it.unibo.pixart.utilities.FileHandler;
import it.unibo.pixart.utilities.ImagePrinter;
import it.unibo.pixart.view.workspace.WorkSpace;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Implementation for WorkSpaceController.
 */
public final class WorkSpaceControllerImpl extends SimpleController implements WorkSpaceController {
    private Matrix currentframe;
    private final ToolFactory toolFactory = new ToolFactoryImpl();
    private AbstractTool tool;
    private boolean isDrawing;
    private int currentIndex;

    @Override
    public void selectTool(final String toolName, final Color color, final int toolSize) {
        this.tool = this.toolFactory.createTool(toolName, color, toolSize);
    }

    @Override
    public void colorGrid(final int x, final int y, final Color color) {
        final Pixel p = this.currentframe.getPixels().stream()
                .filter(e -> e.comparePixel(new PixelBuilder.PxlBuilder().setX(x).setY(y).setColor(color).build()))
                .findAny()
                .get();

        final Set<Pixel> result = tool.updateGrid(p, this.currentframe.getPixels());
        this.currentframe.getMemento().setState(currentframe.getPixels());
        this.currentframe.setPixel(result);
        this.getWorkSpaceView().updateView(result);
    }

    @Override
    public void setCurrentFrame(final int index) {
        final HistoryFrame currentHistoryFrame = getModel().getProject().getAllHistoryFrames().get(currentIndex);
        currentHistoryFrame.setPath(getModel().getProject().getPath()
                + "/" + getModel().getProject().getName()
                + currentHistoryFrame.getIndex()
                + getModel().getProject().getFileType());

        ImagePrinter.getInstance().printOneFrame(currentframe, getModel().getProject().getPath()
                        + File.separatorChar + getModel().getProject().getName()
                        + currentIndex
                        + getModel().getProject().getFileType(),
                getModel().getProject().getFileType(), 4);

        this.currentIndex = index;
        this.currentframe = getModel().getProject().getAllFrames().get(index);
    }

    @Override
    public void setFirstFrame() {
        this.currentframe = this.getModel().getProject().getAllFrames().get(0);
        this.currentIndex = 0;
    }

    @Override
    public void resetCurrentFrame() {
        this.currentframe = new PixelMatrix.MatrixBuilder()
                .setColumns(this.getModel().getProject().getAllFrames().get(0).getColumns())
                .setRows(this.getModel().getProject().getAllFrames().get(0).getRows()).build();
        this.getWorkSpaceView().updateView(this.currentframe.getPixels());
    }

    @Override
    public Set<Pixel> getPreviousState() {
        currentframe.revert();
        return currentframe.getPixels();
    }

    @Override
    public void addNewFrame() {
        final int lastIndex = getModel().getProject().getLastHistoryFrame().getIndex();
        ImagePrinter.getInstance().printOneFrame(currentframe, getModel().getProject().getPath()
                        + File.separatorChar + getModel().getProject().getName()
                        + currentIndex
                        + getModel().getProject().getFileType(),
                getModel().getProject().getFileType(), 4);

        this.getHistoryFrames().get(currentIndex).setPath(getModel().getProject().getPath()
                + File.separatorChar + getModel().getProject().getName()
                + currentIndex
                + getModel().getProject().getFileType());

        this.getModel().getProject().addNewFrame();
        this.getModel().getProject().addNewHistoryFrame(lastIndex + 1);
        setCurrentFrame(this.getModel().getProject().getAllFrames().size() - 1);
        this.currentIndex = this.getModel().getProject().getAllFrames().size() - 1;
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
    public boolean isDrawing() {
        return this.isDrawing;
    }

    @Override
    public Set<Pixel> getCurrentFrame() {
        return this.currentframe.getPixels();
    }

    @Override
    public void saveProject(final int scale) {
        this.currentframe.getMemento().emptyStack();
        ImagePrinter.getInstance().printAllFrames(this.getModel().getProject(), scale);
        try {
            FileHandler.getInstance().fromProjectToJson(this.getModel().getProject());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCurrentFrame() {
        final int prevIndex = this.currentIndex;
        FileHandler.getInstance().deleteFile(getModel().getProject().getAllHistoryFrames().get(currentIndex).getPath());
        if (this.currentIndex == 0 && getModel().getProject().getAllFrames().size() == 1) {
            this.getModel().getProject().addNewFrame();
            this.getModel().getProject().addNewHistoryFrame(currentIndex);
            this.currentframe = getModel().getProject().getAllFrames().get(1);
        } else if (this.currentIndex == 0 && getModel().getProject().getAllFrames().size() > 1) {
            this.currentframe = getModel().getProject().getAllFrames().get(currentIndex + 1);
        } else {
            this.currentframe = getModel().getProject().getAllFrames().get(currentIndex - 1);
            this.currentIndex = currentIndex - 1;
        }
        this.getHistoryFrames().remove(prevIndex);
        this.getModel().getProject().getAllFrames().remove(prevIndex);
    }

    private WorkSpace getWorkSpaceView() {
        return (WorkSpace) getView();
    }
}
