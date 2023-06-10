package it.unibo.pixart.model.user.validator;

/**
 * Enumeration for the validation result.
 */
public enum ValidationResult {

    /**
     * correct credentials.
     */
    CORRECT("Correct"),

    /**
     * username or password too short.
     */
    TOO_SHORT("Too short"),

    /**
     * username or password too long.
     */
    TOO_LONG("Too long"),

    /**
     * path not found.
     */
    PATH_NOT_FOUND("Path not found");

    private final String description;

    ValidationResult(final String description) {
        this.description = description;
    }

    /**
     * @return the description of the validation result. 
     */
    public String getDescription() {
        return this.description;
    }

}
