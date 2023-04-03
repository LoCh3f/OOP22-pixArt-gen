package it.unibo.pixArt.model.workspace;

import javafx.scene.paint.Color;

public interface WorkSpaceModel {
    
    /**
     * @param string Method used to set the tool based on the type selected by the user.
     */
    public void setTool(final String string);

    /**
     * @param newColor Method to set the selected color.
     */
    public void setColor(final Color newColor);

    /**
     * Method to set the inUse flag
     */
    public void setInUse();
}
