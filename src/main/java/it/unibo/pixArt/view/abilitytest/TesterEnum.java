package it.unibo.pixArt.view.abilitytest;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;

public enum TesterEnum {

    VERY_GOOD(0, IMAGE_PATH + IMAGE_VERY_GOOD),

    GOOD(10,IMAGE_PATH + IMAGE_GOOD),

    BAD(30,IMAGE_PATH + IMAGE_BAD),

    VERY_BAD(30,IMAGE_PATH + IMAGE_VERY_BAD);

    private final String path;

    private final int limit;


    TesterEnum(final int limit, final String path) {
        this.limit = limit;
        this.path = path;
    }

    public String getPath() {
        return  this.path;
    }

    public int getLimit() {
        return this.limit;
    }

}
