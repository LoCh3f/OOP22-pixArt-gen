package it.unibo.pixArt.model.tool.drawingTools;

import java.util.HashSet;
import java.util.Set;

import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractDrawingTool;
import it.unibo.pixArt.utilities.Pair;
import javafx.scene.paint.Color;

public class Pencil extends AbstractDrawingTool {

    private Color selectedColor;

    public Pencil(final Color selectedColor, final int size) {
        super(size);
        this.selectedColor = selectedColor;
    }

    @Override
    public Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame) {
        Set<Pixel> newPixSet = new HashSet<>();
        Pair<Integer, Integer> p2Position = super.calculatePosition(pixel, super.toolSize, super.getFrameSize(frame)); 
        Pixel p2 = new ImplPixel(p2Position.getX(), p2Position.getY());
        Pixel tempPix;

        for (var x: range(pixel.getPosition().getX(), p2.getPosition().getX())){
            for (var y: range(pixel.getPosition().getY(), p2.getPosition().getY())){
                tempPix = new ImplPixel(x,y);
                tempPix.setColor(this.selectedColor);
                newPixSet.add(tempPix);
            }
        }

        return newPixSet;

    }
}


