package it.unibo.pixArt.model.project;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/*
 * Enum for all the possible image types.
 */
public enum FileTypes {
    PNG(".png"), JPG(".jpg"), JPEG(".jpeg");

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
       return Stream.of(values()).map(e -> e.getType()).collect(Collectors.toList());
    }
}
