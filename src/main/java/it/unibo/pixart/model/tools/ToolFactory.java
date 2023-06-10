package it.unibo.pixart.model.tools;

import javafx.scene.paint.Color;

/**
 * Factory for the creation of tools.
 */
public interface ToolFactory {

    /**
     * @param toolType      name of the tools
     * @param selectedColor selected color
     * @param toolSize      size of the tools
     * @return the tools
     */
    AbstractTool createTool(String toolType, Color selectedColor, int toolSize);

}
