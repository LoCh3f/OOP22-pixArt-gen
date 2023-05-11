package it.unibo.pixArt.model.user.validator;

public enum ValidationResult {
    
    CORRECT("Correct"),

    TOO_SHORT("Too short"),

    TOO_LONG("Too long");

    private final String description;

    ValidationResult(final String description) {
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }
    

}
