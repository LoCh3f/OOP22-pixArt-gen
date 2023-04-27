package it.unibo.pixArt.model.workspace;


import java.util.Optional;

import it.unibo.pixArt.controller.workspace.WorkSpaceController;
import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryProvider;
import it.unibo.pixArt.model.user.User;
import javafx.scene.paint.Color;

public class WorkSpaceModelImpl<T> extends ModelImpl implements WorkSpaceModel{
    private PixelGrid frame;
    private ToolFactory<T> toolFactory;
    private T tool;
    private Color selectedColor;
    private Boolean inUse;

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInUse'");
    }


}
