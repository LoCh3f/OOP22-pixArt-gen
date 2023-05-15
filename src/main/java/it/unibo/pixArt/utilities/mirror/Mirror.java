package it.unibo.pixArt.utilities.mirror;

import java.util.Collection;
import java.util.Set;

public interface Mirror<X> {

    /**
     * @param fromM original collection saved in memory,
     * @param fromV collection made by user to confront,
     * @return new set made by element from original collection that are missing in the second;
     */
    Set<X> getDifference(final Collection<X> fromM, final Collection<X> fromV);
}
