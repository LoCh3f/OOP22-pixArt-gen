package it.unibo.pixArt.model.tool;
import javafx.scene.paint.Color;

public interface ToolFactory {
    
    public AbstractTool createTool(final String toolType, final Color selectedColor, final int toolSize);
    
}
