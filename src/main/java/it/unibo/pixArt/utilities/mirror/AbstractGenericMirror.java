package it.unibo.pixArt.utilities.mirror;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * This is a simple implementation pf interface mirror,
 * the generic type choose  must override method equals from s.c. Object to work in this class;
 */
public class AbstractGenericMirror<X> implements Mirror<X> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<X> getDifference(final Collection<X> fromM, final Collection<X> fromV) {
        return fromM.stream().filter(m -> fromV.stream().anyMatch(v -> !v.equals(m))).collect(Collectors.toSet());
    }


}
