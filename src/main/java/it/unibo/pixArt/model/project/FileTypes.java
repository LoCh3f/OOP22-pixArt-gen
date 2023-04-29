package it.unibo.pixArt.model.project;

import java.util.LinkedList;
import java.util.List;

public enum FileTypes {
    PNG(".png"),JPG(".jpg"),JPEG(".jpeg");

    private String type;

    FileTypes(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    /**
     * @return A list containing all the file types.
     */
    public static List<String> getAllTypes() {
        var list = new LinkedList<String>();
        for(var elem : FileTypes.values()) {
            list.add(elem.getType());
        }
        return list;
    }
}
