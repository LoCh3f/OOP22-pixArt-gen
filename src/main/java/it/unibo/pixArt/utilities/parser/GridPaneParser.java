package it.unibo.pixArt.utilities.parser;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.FX_BORDER_COLOR;
import static it.unibo.pixArt.utilities.variables.FXViewVariables.FX_BORDER_WIDTH;

/**
 * This Class help to convert a GridPane in a
 * Collection of Pixel
 */
public class GridPaneParser implements Function<GridPane, Collection<Pixel>> {
    @Override
    public Collection<Pixel> apply(GridPane gridPane) {
        final var pixels = new HashSet<Pixel>();
        for (Node n : gridPane.getChildren()) {
            pixels.add(new PixelBuilder.PxlBuilder().
                    setX(GridPane.getColumnIndex(n)).setY(GridPane.getRowIndex(n)).
                    setColor(parseButtonBackGround(n.getStyle())).build());
        }
        return pixels;
    }

    private Color parseButtonBackGround(final String style) {
        return Color.web(style.replace(FX_BORDER_COLOR, "").replace(";", "").replace(FX_BORDER_WIDTH, ""));
    }
}
