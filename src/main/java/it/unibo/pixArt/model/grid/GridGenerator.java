package it.unibo.pixArt.model.grid;

public class GridGenerator {

    private static final int SMALL = 16;

    private static final int MEDIUM = 32;

    private static final int LARGE = 64;

    public static PixelGrid smallGrid() {
        return new GridImpl(SMALL, SMALL);
    }


    public static PixelGrid mediumGrid() {
        return new GridImpl(MEDIUM, MEDIUM);
    }



    public static PixelGrid largeGrid() {
        return new GridImpl(LARGE, LARGE);
    }
}
