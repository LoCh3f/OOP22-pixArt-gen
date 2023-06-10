package it.unibo.pixart.model.tools;


import it.unibo.pixart.model.tools.drawingtools.DarkenTool;
import it.unibo.pixart.model.tools.drawingtools.Eraser;
import it.unibo.pixart.model.tools.drawingtools.LightenTool;
import it.unibo.pixart.model.tools.drawingtools.Pencil;
import it.unibo.pixart.model.tools.drawingtools.SprayTool;
import it.unibo.pixart.model.tools.filltools.Bucket;
import javafx.scene.paint.Color;

/**
 * Implementation of ToolFactory.
 */
public final class ToolFactoryImpl implements ToolFactory {

    @Override
    public AbstractTool createTool(final String toolType, final Color selectedColor, final int toolSize) {
        switch (toolType) {
            case "PENCIL":
                return new Pencil(selectedColor, toolSize);
            case "ERASER":
                return new Eraser(toolSize);
            case "LIGHTEN":
                return new LightenTool(toolSize);
            case "DARKEN":
                return new DarkenTool(toolSize);
            case "SPRAY":
                return new SprayTool(selectedColor, toolSize);
            case "BUCKET":
                return new Bucket(selectedColor);
            default:
                break;
        }
        return null;
    }

}
