package it.unibo.pixart.model.tool;
import javafx.scene.paint.Color;

/**
 * Factory for the creation of tools.
 */
public interface ToolFactory {

    /**
     * @param toolType name of the tool
     * @param selectedColor selected color
     * @param toolSize size of the tool
     * @return the tool
     */
    AbstractTool createTool(String toolType, Color selectedColor, int toolSize);

}
