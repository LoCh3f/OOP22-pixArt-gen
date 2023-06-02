package it.unibo.pixArt.model.tool;

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
     * spray tool.
     */
    SPRAYTOOL("SPRAY"),

    /**
     * darken tool.
     */
    DARKENTOOL("DARKEN"),

    /**
     * lighten tool.
     */
    LIGHTENTOOL("LIGHTEN"),

    /**
     * bucket.
     */
    BUCKET("BUCKET");

    private final String name;

    /**
     * @param name of the tool
     */
    ToolEnum(final String name) {
        this.name = name;
    }

    /**
     * @return the name of the tool
     */
    public String getName() {
        return this.name;
    }

}
