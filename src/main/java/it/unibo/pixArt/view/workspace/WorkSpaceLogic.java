package it.unibo.pixArt.view.workspace;

import javafx.collections.ObservableList;
import javafx.scene.Node;

public class WorkSpaceLogic implements Logic {


    private boolean isDrawing = false;

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
}
