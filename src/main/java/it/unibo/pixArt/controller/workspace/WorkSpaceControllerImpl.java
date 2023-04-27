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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectTool'");
    }

    @Override
    public void colorGrid() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'colorGrid'");
    }

    private WorkSpaceModel getWorkSpaceModel() {
        return (WorkSpaceModel) this.getModel();
    }

    @Override
    public void setCurrentFrame(int index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCurrentFrame'");
    }

    @Override
    public Set<Pixel> getFrameState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getFrameState'");
    }


    @Override
    public Color getCurrentColor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurrentColor'");
    }
    
}
