package it.unibo.pixArt.model.tool;
/*
 * An enum containing all the tools that need the frame's size in order to work.
 */
public enum DimensionDipT {
    BUCKET("Bucket");

    private String name;

    DimensionDipT(final String tool) {
        this.name = name;
    }

    /**
     * @param tool the name of the tool
     * @return wheter the tool is present in this enum
     */
    public static boolean isPresent(final String tool){
        for(var elem : DimensionDipT.values()) {
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
