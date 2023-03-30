package it.unibo.pixArt.model.tool;

public enum ToolEnum {
    
    BRUSH("Brush"),

    ERASER("Eraser"),

    BUCKET("Bucket");

    private final String name;

    /**
     * @param name
     */
    ToolEnum(final String name) {
        this.name = name;
    }

    /**
     * @return the name of the tool
     */
    public String getName(){
        return this.name;
    }


}
