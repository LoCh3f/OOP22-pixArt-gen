package it.unibo.pixArt.utilities;

import java.util.Collection;
import java.util.Set;

public interface Mirror<X> {

    Set<X> getDifference(final Collection<X> fromM, final Collection<X> fromV);
}
