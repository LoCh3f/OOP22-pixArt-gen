package it.unibo.pixArt.eastern.logic;

import it.unibo.pixArt.model.grid.PixelGrid;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class LogicImpl implements Logic {

    public static final String TAB = "\t";

    public static final String NEW_LINE = "\n";

    public static final String WELCOME_MESSAGE = "Welcome to the CoordinatesArt";

    public static final String WAITING_MESSAGE = "Waiting for input...";

    public static final String ERROR_MESSAGE = "Error: ";

    public static final String HELP_MESSAGE = "Type 'help' to print the list of commands";


    public LogicImpl() {
    }

    public static String readInput() {
        System.out.println(WAITING_MESSAGE + NEW_LINE);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.lines().collect(Collectors.joining("\n"));
    }

    @Override
    public void printMatrix(final PixelGrid grid) {
        grid.getPixels().forEach(p -> {
            if (p.getPosition().getX() == Math.sqrt(grid.getPixels().size() - 1)) {
                System.out.print("[" + p.getPosition().getX() + "," + p.getPosition().getY() + "]");
                System.out.println();
            } else {
                System.out.print("[" + p.getPosition().getX() + "," + p.getPosition().getY() + "]");
                System.out.print(TAB);
            }
        });
        System.out.println();
        System.out.print(TAB + HELP_MESSAGE + NEW_LINE);
    }


}
