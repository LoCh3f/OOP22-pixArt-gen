package it.unibo.pixArt.utilities;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class AbstractGenericMirror<X> implements Mirror<X> {
    @Override
    public Set<X> getDifference(Collection<X> fromM, Collection<X> fromV) {
        return fromM.stream().filter(m -> fromV.stream().anyMatch(v -> !v.equals(m))).collect(Collectors.toSet());
    }
}
