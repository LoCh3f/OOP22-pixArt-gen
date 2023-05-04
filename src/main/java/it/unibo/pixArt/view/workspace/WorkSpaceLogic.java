package it.unibo.pixArt.view.workspace;

import javafx.collections.ObservableList;
import javafx.scene.Node;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static it.unibo.pixArt.utilities.FXStyleVariable.*;

public class WorkSpaceLogic implements Logic {


    private boolean isDrawing = false;

    public static final Set<String> imagePaths = new HashSet<String>(List.of(IMAGE_PATH + TOAD_IMAGE, IMAGE_PATH + SONIC_IMAGE, IMAGE_PATH + HOMER_IMAGE, IMAGE_PATH + FLOPPY_BIRD));

    private final int columns;
    private final int rows;


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

    public boolean isValid(final int index, ObservableList<Node> buttons, final int father) {

        return index == buttons.indexOf(buttons.get(father + 1)) ||
                index == buttons.indexOf(buttons.get(father - 1)) ||
                index == buttons.indexOf(buttons.get(father + rows)) ||
                index == buttons.indexOf(buttons.get(father + columns)) ||
                index == buttons.indexOf(buttons.get(father - columns)) ||
                index == buttons.indexOf(buttons.get(father + columns + 1)) ||
                index == buttons.indexOf(buttons.get(father + columns - 1)) ||
                index == buttons.indexOf(buttons.get(father - columns + 1)) ||
                index == buttons.indexOf(buttons.get(father - columns - 1));


    }

    @Override
    public String getImagePath() {
        return imagePaths.stream().toList().get(new Random().nextInt(0, imagePaths.size()));
    }


}
