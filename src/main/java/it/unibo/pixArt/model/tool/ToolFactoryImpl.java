package it.unibo.pixArt.model.tool;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public class ToolFactoryImpl implements ToolFactory{

   @Override
    public Tool createNormalBrush() {
        return new Tool(){
            @Override
            public Set<Pixel> color(final Pixel pixel, final Color color) {
                pixel.setColor(color);
                return Collections.singleton(pixel);
            }
        };
    }

    @Override
    public Tool createTool(final String name) {

        switch (name) {
            case "BRUSH": 
                return this.createNormalBrush();
            default:
               return this.createNormalBrush();

        }
        
    }




}
