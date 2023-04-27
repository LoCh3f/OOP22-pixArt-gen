package it.unibo.pixArt.controller.workspace;

import java.util.Set;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.DimensionDipT;
import it.unibo.pixArt.model.tool.DimensionIndipT;
import it.unibo.pixArt.model.workspace.WorkSpaceModel;
import it.unibo.pixArt.model.workspace.WorkSpaceModelImpl;
import javafx.scene.paint.Color;

public class WorkSpaceControllerImpl extends SimpleController implements WorkSpaceController {

    @Override
    public void selectCurrentColor(final Color newColor) {
        this.getWorkSpaceModel().setColor(newColor);
    }


    @Override
    public void selectTool(String toolName) throws Exception {
        if(DimensionDipT.isPresent(toolName)) {
            this.getWorkSpaceModel().setTool(toolName, true);
        } else if(DimensionIndipT.isPresent(toolName)) {
            this.getWorkSpaceModel().setTool(toolName, false);
        } else {
            throw new Exception();
        }
    }

    @Override
    public void colorGrid(final int x, final int y) {
        this.getWorkSpaceModel().colorGrid(new ImplPixel(x,y));
    }

    
    @Override
    public void setCurrentFrame(final int index) {
        this.getWorkSpaceModel().setCurrentFrame(index);
    }
    
    @Override
    public Set<Pixel> getFrameState() {
        return this.getWorkSpaceModel().getFrameState();
    }
    
    private WorkSpaceModel getWorkSpaceModel() {
        return (WorkSpaceModel) this.getModel();
    }
    
}
