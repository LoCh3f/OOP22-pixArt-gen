package it.unibo.pixArt.controller.workspace;

import java.util.Set;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.workspace.WorkSpaceModel;
import it.unibo.pixArt.model.workspace.WorkSpaceModelImpl;
import javafx.scene.paint.Color;

public class WorkSpaceControllerImpl extends SimpleController implements WorkSpaceController {

    @Override
    public void selectCurrentColor(final Color newColor) {
        this.getWorkSpaceModel().setColor(newColor);
    }


    @Override
    public void selectTool(String toolName) {
        
    }

    @Override
    public void colorGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colorGrid'");
    }

    
    @Override
    public void setCurrentFrame(final int index) {
        throw new UnsupportedOperationException("Unimplemented method 'colorGrid'");
    }
    
    @Override
    public Set<Pixel> getFrameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrameState'");
    }

    public void colorGrid(Pixel p) {
        throw new UnsupportedOperationException("Unimplemented method 'getFrameState'");
    }
    
    private WorkSpaceModel getWorkSpaceModel() {
        return (WorkSpaceModel) this.getModel();
    }
    
}
