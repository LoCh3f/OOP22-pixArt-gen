package it.unibo.pixArt.model.tool;

import java.util.Set;

import it.unibo.pixArt.model.pixel.Pixel;

public abstract class AbstractTool {

    /**
     * @param pixel
     * @param frame
     * @return a set of modified pixels
     */
    public abstract Set<Pixel> updateGrid(Pixel pixel, Set<Pixel> frame);

    /**
     * @param frame
     * @return the size of the frame
     */
    public int getFrameSize(final Set<Pixel> frame) {
        return (int) Math.sqrt(frame.size());
    }

}
