package it.unibo.pixArt.view.workspace;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

import static it.unibo.pixArt.utilities.FXStyleVariable.*;

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

        static GridPane parsePixels(final Set<Pixel> pixels) {
            final var gridPane = new GridPane();
            for (Pixel p : pixels) {
                gridPane.add(new Button(), p.getPosition().getX(), p.getPosition().getY());
                gridPane.getChildren().get(gridPane.getChildren().size() - 1).setStyle(parseColor(p.getColor()));
            }
            return gridPane;
        }

        private static Color parseButtonBackGround(final String style) {
            return Color.web(style.replace(FX_BORDER_COLOR, "").replace(";", "").replace(FX_BORDER_WIDTH, ""));
        }

        private static String parseColor(final Color color) {
            return "".concat(FX_BACKGROUND_COLOR_START).concat(color.toString()).concat(";").concat(FX_BORDER_COLOR).concat(";").concat(FX_BORDER_WIDTH);
        }

    }
}
