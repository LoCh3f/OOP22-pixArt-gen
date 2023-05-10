package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.AbstractGenericMirror;
import it.unibo.pixArt.utilities.Mirror;

import java.util.*;

import static it.unibo.pixArt.utilities.FXStyleVariable.*;

public class WorkSpaceLogic implements Logic {


    private boolean isDrawing = false;

    public static final Set<String> imagePaths = new HashSet<>(List.of(IMAGE_PATH + TOAD_IMAGE, IMAGE_PATH + SONIC_IMAGE, IMAGE_PATH + HOMER_IMAGE, IMAGE_PATH + FLOPPY_BIRD));

    private final int columns;
    private final int rows;
    private final Mirror<Pixel> gridMirror = new AbstractGenericMirror<>();

    public WorkSpaceLogic(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
    }


    @Override
    public boolean isDrawing() {
        return this.isDrawing;
    }

    @Override
    public void changeState() {
        this.isDrawing = !this.isDrawing;
    }


    @Override
    public String getImagePath() {
        return imagePaths.stream().toList().get(new Random().nextInt(0, imagePaths.size()));
    }

    public String test(final Collection<Pixel> userGrid) {
        return null; //templatePath(this.gridMirror.getDifference(   ,userGrid).size());
    }


    private String templatePath(final int difference) {
        return switch (difference) {
            case 0 -> IMAGE_PATH + IMAGE_VERY_GOOD;
            case 10 -> IMAGE_PATH + IMAGE_GOOD;
            case 30 -> IMAGE_PATH + IMAGE_BAD;
            default -> IMAGE_PATH + IMAGE_VERY_BAD;
        };
    }
}
