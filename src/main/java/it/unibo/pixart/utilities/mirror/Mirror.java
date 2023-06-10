package it.unibo.pixart.utilities.mirror;

import java.util.Collection;
import java.util.Set;

/**
 * @param <X> the type of the collection.
 */
public interface Mirror<X> {

    /**
     * @param fromM original collection saved in memory,
     * @param fromV collection made by user to confront,
     * @return new set made by element from original collection that are missing in the second;
     */
    Set<X> getDifference(Collection<X> fromM, Collection<X> fromV);
}
