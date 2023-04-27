package it.unibo.pixArt.model.tool;

import it.unibo.pixArt.model.tool.drawingTools.Brush;
import it.unibo.pixArt.model.tool.drawingTools.DarkenTool;
import it.unibo.pixArt.model.tool.drawingTools.Eraser;
import it.unibo.pixArt.model.tool.drawingTools.LightenTool;
import it.unibo.pixArt.model.tool.drawingTools.Pencil;
import it.unibo.pixArt.model.tool.drawingTools.SprayTool;
import javafx.scene.paint.Color;

public class DrawingToolFactory implements ToolFactory<DrawingTool>{

    public DrawingTool createTool(final String toolType, final Color selectedColor) {

        switch (toolType) {
            case "PENCIL":
                return new Pencil(selectedColor);
            case "BRUSH":
                return new Brush();
            case "ERASER":
                return new Eraser();
            case "LIGHTEN":
                return new LightenTool();
            case "DARKEN":
                return new DarkenTool();
            case "SPRAY":
                return new SprayTool(selectedColor);
            default:
                break;
        }
        return null;

    }
    
}
