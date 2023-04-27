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

    /**
     * @param tool the name of the tool
     * @return wheter the tool is present in this enum
     */
    public static boolean isPresent(final String tool){
        for(var elem : DimensionIndipT.values()) {
            if(elem.name == tool) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return the name of the tool
     */
    public String getName() {
        return this.name;
    }
}
