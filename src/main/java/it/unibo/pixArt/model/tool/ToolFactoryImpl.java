package it.unibo.pixArt.model.tool;

import it.unibo.pixArt.model.tool.drawingTools.DarkenTool;
import it.unibo.pixArt.model.tool.drawingTools.Eraser;
import it.unibo.pixArt.model.tool.drawingTools.LightenTool;
import it.unibo.pixArt.model.tool.drawingTools.Pencil;
import it.unibo.pixArt.model.tool.drawingTools.SprayTool;
import it.unibo.pixArt.model.tool.fillTools.Bucket;
import javafx.scene.paint.Color;

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
