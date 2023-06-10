package it.unibo.pixart.view.abilitytest;

import static it.unibo.pixart.utilities.variables.FXViewVariables.*;

/**
 * Enum for the tester, with path and limit.
 */
public enum TesterEnum {


    /**
     *
     */
    VERY_GOOD(1, IMAGE_PATH + IMAGE_VERY_GOOD),

    /**
     *
     */
    GOOD(30, IMAGE_PATH + IMAGE_GOOD),

    /**
     *
     */
    TRY_BETTER(80, IMAGE_PATH + IMAGE_TRY_BETTER),

    /**
     *
     */
    BAD(120, IMAGE_PATH + IMAGE_BAD),

    /**
     *
     */
    VERY_BAD(160, IMAGE_PATH + IMAGE_VERY_BAD),

    /**
     *
     */
    TOO_MUCH(200, IMAGE_PATH + IMAGE_TOO_MUCH),

    /**
     *
     */
    WASTED(256, IMAGE_PATH + IMAGE_WASTED);

    private final String path;

    private final int limit;


    /**
     * @param limit the limit of the difference
     * @param path  the path of the image
     */
    TesterEnum(final int limit, final String path) {
        this.limit = limit;
        this.path = path;
    }

    /**
     * @return the path of the image
     */
    public String getPath() {
        return this.path;
    }

    /**
     * @return the limit of the difference
     */
    public int getLimit() {
        return this.limit;
    }

}
