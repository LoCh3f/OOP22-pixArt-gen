package it.unibo.pixArt.model.tool;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public abstract class AbstractTool {

    public abstract Set<Pixel> updateGrid(final Pixel pixel, Set<Pixel> frame);

}
