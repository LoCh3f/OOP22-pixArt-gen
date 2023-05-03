package it.unibo.pixArt.view.workspace;

public class WorkSpaceLogic implements Logic {

    private boolean isDrawing = false;

    public WorkSpaceLogic() {
    }


    @Override
    public Boolean isDrawing() {
        return this.isDrawing;
    }

    @Override
    public void changeState() {
        this.isDrawing = !this.isDrawing;
    }

    public void setState(final boolean isDrawing) {
        this.isDrawing = isDrawing;
    }
}
