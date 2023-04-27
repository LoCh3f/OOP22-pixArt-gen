package it.unibo.pixArt.model.tool;
import javafx.scene.paint.Color;

public interface ToolFactory<T> {
    
    T createTool(String toolType, Color selectedColor);
    
}
