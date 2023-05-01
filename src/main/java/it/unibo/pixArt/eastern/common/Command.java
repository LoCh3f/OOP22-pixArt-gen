package it.unibo.pixArt.eastern.common;

enum Command {
    EXIT("exit", "Exit the program"),
    HELP("help", "Show this help"),
    PRINT("print", "Print the matrix"),
    CLEAR("clear", "Clear the matrix"),
    RANDOM("random", "Fill the matrix with random colors"),
    SAVE("save", "Save the matrix to a file");

    private final String name;
    private final String description;

    Command(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public static Command getCommand(final String name) {
        for (final Command command : Command.values()) {
            if (command.getName().equals(name)) {
                return command;
            }
        }
        return null;
    }
}
