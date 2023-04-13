package it.unibo.pixArt.model.tool;
import java.util.Set;
import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public interface Tool {
    Set<Pixel> color(Pixel pixel, Color color);
}
