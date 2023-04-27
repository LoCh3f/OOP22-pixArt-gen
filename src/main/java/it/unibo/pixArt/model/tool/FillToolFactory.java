package it.unibo.pixArt.model.tool;

import it.unibo.pixArt.model.tool.fillTools.Bucket;
import javafx.scene.paint.Color;

public class FillToolFactory implements ToolFactory<FillTool> {

    public FillTool createTool(final String toolType, final Color selectedColor) {

        switch (toolType) {
            case "BUCKET":
                return new Bucket(selectedColor);
            default:
                break;
        }
        return null;

    }

}