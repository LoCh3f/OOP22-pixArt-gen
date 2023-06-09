package it.unibo.pixArt.model.timer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TimerType Enumeration.
 */
public enum TimerType {

    /**
     * one minute.
     */
    ONE_MINUTE(60L, "1 min"),

    /**
     * three minutes.
     */
    THREE_MINUTES(180L, "3 min"),

    /**
     * five minutes.
     */
    FIVE_MINUTES(300L, "5 min");

    private final long time;
    private final String description;

    /**
     * @param time
     * @param description
     */
    TimerType(final long time, final String description) {
        this.time = time;
        this.description = description;
    }

    /**
     * @return the time of the timer type
     */
    public long getTime() {
        return this.time;
    }

    /**
     * @return the description of the timer type
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * @return a list with all the timer types
     */
    public static List<TimerType> getAllTypes() {
        return Stream.of(values()).collect(Collectors.toList());
    }

}
