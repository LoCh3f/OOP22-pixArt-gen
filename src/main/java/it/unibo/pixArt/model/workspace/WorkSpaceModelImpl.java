package it.unibo.pixArt.model.workspace;

import java.util.Optional;

import it.unibo.pixArt.controller.impl.WorkSpaceController;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.tool.Tool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import it.unibo.pixArt.model.user.User;
import javafx.scene.paint.Color;

public class WorkSpaceModelImpl extends ModelImpl implements WorkSpaceModel{
    private WorkSpaceController controller;
    private Project project;
    private PixelGrid frame;
    private final ToolFactory toolFactory = new ToolFactoryImpl();
    private Tool tool;
    private Color selectedColor;
    private Boolean inUse;

    public WorkSpaceModelImpl(final Optional<Project> proj){
        if(proj.isPresent()) {
            this.project = proj.get();
            this.frame = proj.get().getAllFrames().get(0);
        }
    }

    @Override
    public void setTool(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeTool'");
    }

    @Override
    public void setColor(Color newColor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'changeColor'");
    }

    @Override
    public void setInUse() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInUse'");
    }


}
