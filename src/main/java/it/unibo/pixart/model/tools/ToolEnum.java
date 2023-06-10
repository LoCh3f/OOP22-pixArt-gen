package it.unibo.pixart.model.tools;

/**
 * Enum for all the variables of tools.
 */
public enum ToolEnum {

    /**
     * pencil.
     */
    PENCIL("PENCIL"),

    /**
     * eraser.
     */
    ERASER("ERASER"),

    /**
     * spray tools.
     */
    SPRAYTOOL("SPRAY"),

    /**
     * darken tools.
     */
    DARKENTOOL("DARKEN"),

    /**
     * lighten tools.
     */
    LIGHTENTOOL("LIGHTEN"),

    /**
     * bucket.
     */
    BUCKET("BUCKET");

    private final String name;

    /**
     * @param name of the tools
     */
    ToolEnum(final String name) {
        this.name = name;
    }

    /**
     * @return the name of the tools
     */
    public String getName() {
        return this.name;
    }

}
