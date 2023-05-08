package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

import static it.unibo.pixArt.utilities.FXStyleVariable.FX_BORDER_COLOR;
import static it.unibo.pixArt.utilities.FXStyleVariable.FX_BORDER_WIDTH;

public interface Parser {
    class GridParser {

        static Set<Pixel> parseGrid(final GridPane grid) {
            final var pixels = new HashSet<Pixel>();
            for (Node n : grid.getChildren()) {
                pixels.add(new PixelBuilder.PxlBuilder().
                        setX(GridPane.getColumnIndex(n)).setY(GridPane.getRowIndex(n)).
                        setColor(parseButtonBackGround(n.getStyle())).build());
            }
            return pixels;
        }

        private static Color parseButtonBackGround(final String style) {
            return Color.web(style.replace(FX_BORDER_COLOR, "").replace(";", "").replace(FX_BORDER_WIDTH, ""));
        }

    }
}
