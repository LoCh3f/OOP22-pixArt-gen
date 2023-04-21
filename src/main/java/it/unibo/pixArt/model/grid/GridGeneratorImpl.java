package it.unibo.pixArt.model.grid;

public class GridGeneratorImpl implements GridGenerator {

    private static final int SMALL = 16;

    private static final int MEDIUM = 32;

    private static final int LARGE = 64;

    public GridGeneratorImpl() {
    }

    @Override
    public PixelGrid smallGrid() {
        return new GridImpl(SMALL, SMALL);
    }

    @Override
    public PixelGrid mediumGrid() {
        return new GridImpl(MEDIUM, MEDIUM);
    }

    @Override
    public PixelGrid largeGrid() {
        return new GridImpl(LARGE, LARGE);
    }
}
