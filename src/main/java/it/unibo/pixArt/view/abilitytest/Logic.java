package it.unibo.pixArt.view.abilitytest;

import it.unibo.pixArt.model.pixel.Pixel;

import java.util.Collection;

/**
 *
 */
public interface Logic {

    /**
     * @param userGrid     draw made by the user, rapresented by a collection of pixels
     * @param solutionGrid draw to be made, rapresented by a collection of pixels
     * @return the path of the image to be shown
     */
    String test(Collection<Pixel> userGrid, Collection<Pixel> solutionGrid);

}
