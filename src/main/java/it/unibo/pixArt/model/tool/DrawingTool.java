package it.unibo.pixArt.model.tool;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public interface DrawingTool {
    Set<Pixel> updatePixel(Pixel pixel, int size, Set<Pixel> frame);
}
