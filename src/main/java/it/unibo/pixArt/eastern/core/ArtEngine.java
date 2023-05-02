package it.unibo.pixArt.eastern.core;

import it.unibo.pixArt.eastern.common.AnsiColors;
import it.unibo.pixArt.eastern.logic.Logic;
import it.unibo.pixArt.eastern.logic.LogicImpl;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;

import java.util.Locale;

public class ArtEngine implements EngineaApi {

    private final Logic logics = new LogicImpl();
    private final PixelGrid grid;
    private boolean gameOver;

    public ArtEngine() {
        this.grid = new PixelMatrix.MatrixBuilder().setColumns(16).setRows(16).build();
        this.gameOver = false;
    }


    @Override
    public void mainLoop() {
        System.out.println(AnsiColors.ANSI_PURPLE + LogicImpl.WELCOME_MESSAGE + AnsiColors.ANSI_RESET);
        System.out.println(AnsiColors.ANSI_PURPLE + LogicImpl.HELP_MESSAGE + AnsiColors.ANSI_RESET);
        while (!gameOver) {
            processInput();
        }

    }

    @Override
    public void processInput() {
        final String input = LogicImpl.readInput().toLowerCase(Locale.ROOT);
        switch (input) {
            case "help" -> System.out.println(LogicImpl.HELP_MESSAGE);
            case "print" -> logics.printMatrix(grid);
            case "exit" -> changeState();
            default -> System.out.println(LogicImpl.ERROR_MESSAGE + input);
        }
    }

    private void changeState() {
        this.gameOver = true;
    }
}