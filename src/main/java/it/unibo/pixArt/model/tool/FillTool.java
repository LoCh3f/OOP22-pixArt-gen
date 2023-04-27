package it.unibo.pixArt.model.tool;

import java.util.HashMap;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public interface FillTool {
    Set<Pixel> updatePixel(Pixel pixel, HashMap<Pixel, Pixel> frame);
}
