package it.unibo.pixArt.controller.animation;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

public enum PreviewSizes {
    DEFAULT("Default"),FOUR("4x4"),TWO("2x2"),ONE("1x1");

    private String text;

    PreviewSizes(final String text) {
        this.text = text;
    }

    /**
     * @return list of preview sizes as text.
     */
    public static List<String> getListSizes() {
        return Stream.of(values()).map(e -> e.text).toList();
    }
}
