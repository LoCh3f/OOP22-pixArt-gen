package it.unibo.pixArt.model.workspace;

import java.util.Set;

import it.unibo.pixArt.model.Model;
import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public interface WorkSpaceModel extends Model {
    
    /**
     * @param string Method used to set the tool based on the type selected by the user.
     */
    public void setTool(final String toolName);

    /**
     * @param newColor Method to set the selected color.
     */
    public void setColor(final Color newColor);

    /**
     * Method to set the inUse flag
     */
    public void setInUse();

    /**
     * @param index
     */
    public void setCurrentFrame(final int index);

    /**
     * @return pixels of current frame
     */
    public PixelGrid getFrameState();

    /**
     * @param pixel
     */
    public void colorGrid(final Pixel pixel);

}
