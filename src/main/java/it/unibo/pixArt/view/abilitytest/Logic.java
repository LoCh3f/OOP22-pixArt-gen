package it.unibo.pixArt.view.abilitytest;

import it.unibo.pixArt.model.pixel.Pixel;

import java.util.Collection;

interface Logic {

    String getImagePath();

    String test(final Collection<Pixel> userGrid);

}
