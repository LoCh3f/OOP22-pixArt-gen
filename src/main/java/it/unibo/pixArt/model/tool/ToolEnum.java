package it.unibo.pixArt.model.tool;

public enum ToolEnum {
    
    PENCIL("PENCIL"),

    ERASER("ERASER"),

    SPRAYTOOL("SPRAY"),

    DARKENTOOL("DARKEN"),

    LIGHTENTOOL("LIGHTEN"),

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
