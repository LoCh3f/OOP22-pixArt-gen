package it.unibo.pixArt.model.timer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum TimerType {

    ONE_MINUTE(60000L, "1 min"),

    THREE_MINUTES(180000L, "3 min"),

    FIVE_MINUTES(300000L, "5 min");

    private final long time;
    private final String description;

    TimerType(final long time, final String description){
        this.time = time;
        this.description = description;
    }

    public long getTime(){
        return this.time;
    }

    public String getDescription(){
        return this.description;
    }

    public List<TimerType> getAllTypes(){
        return Stream.of(values()).collect(Collectors.toList());
    }
    
}
