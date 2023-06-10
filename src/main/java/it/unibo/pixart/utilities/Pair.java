package it.unibo.pixart.utilities;

/**
 * A standard generic Pair<X,Y>, with getters, hashCode, equals, and toString well implemented.
 * From OOP lab exam.
<<<<<<< HEAD
 * @param <X>
 * @param <Y>
=======
 *
 * @param <X> type of the first elem
 * @param <Y> type of the second elem
>>>>>>> 93f82aab9e7162f5f7aad6b4dd81e6057172d4ed
 */
public final class Pair<X, Y> {

    private final X x;
    private final Y y;

    /**
     * Constructor.
     *
     * @param x
     * @param y
     */
    public Pair(final X x, final Y y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
<<<<<<< HEAD
     * @return x
=======
     * @return elem X
>>>>>>> 93f82aab9e7162f5f7aad6b4dd81e6057172d4ed
     */
    public X getX() {
        return x;
    }

    /**
<<<<<<< HEAD
     * @return y
=======
     * @return
>>>>>>> 93f82aab9e7162f5f7aad6b4dd81e6057172d4ed
     */
    public Y getY() {
        return y;
    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pair other = (Pair) obj;
        if (x == null) {
            if (other.x != null) {
                return false;
            }
        } else if (!x.equals(other.x)) {
            return false;
        }
        if (y == null) {
            return other.y == null;
        } else {
            return y.equals(other.y);
        }
    }

    /**
     * @return the pair string rappresentations
     */
    @Override
    public String toString() {
        return "Pair [x=" + x + ", y=" + y + "]";
    }


}
