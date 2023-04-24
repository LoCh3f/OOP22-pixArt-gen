package it.unibo.pixArt.model.tool;
import java.util.Set;
import it.unibo.pixArt.model.pixel.Pixel;

public interface Tool {
    Set<Pixel> color(Pixel pixel);
}
