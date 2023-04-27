package it.unibo.pixArt.model.tool;

import java.util.Map;
import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public interface FillTool {
    Set<Pixel> updatePixel(Pixel pixel, Map<Pixel, Pixel> frame);
}
