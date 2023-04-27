package it.unibo.pixArt.model.tool;

/*
 * An enum containing all the tools that don't need the frame's size.
 */
public enum DimensionIndipT {
    BRUSH("Brush"),
    DARKEN_TOOL("Darken Tool"),
    ERASER("Eraser"),
    LIGHTEN_TOOL("Lighten Tool"),
    PENCIL("Pencil"),
    SPRAY_TOOL("Spray Tool");

    private String name;

    DimensionIndipT(final String name) {
        this.name = name;
    }
}
