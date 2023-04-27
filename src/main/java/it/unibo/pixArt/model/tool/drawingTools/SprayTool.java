package it.unibo.pixArt.model.tool.drawingTools;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public class SprayTool extends AbstractDrawingTool{

    private final Color selectedColor;

    public SprayTool(final Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    @Override
    public Set<Pixel> updatePixel(Pixel pixel, int size, Set<Pixel> frame) {
        Set<Pixel> newPixSet = new HashSet<>();
        Pair<Integer, Integer> p2Position = calculatePosition(pixel, size*2, frame.size());
        Pixel p2 = new ImplPixel(p2Position.getX(), p2Position.getY());
        Pixel tempPix;
        Random rand = new Random();

        for (var x: range(pixel.getPosition().getX(), p2.getPosition().getX())){
            for (var y: range(pixel.getPosition().getY(), p2.getPosition().getY())){
                tempPix = new ImplPixel(x,y);
                if(rand.nextBoolean()) {
                    tempPix.setColor(selectedColor);
                    newPixSet.add(tempPix);
                }
            }
        }
        return newPixSet;
    }

}
