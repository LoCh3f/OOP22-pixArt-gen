package it.unibo.pixart.utilities.parser;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import static it.unibo.pixart.utilities.variables.FXViewVariables.*;

import java.util.Collection;
import java.util.function.Function;

import it.unibo.pixart.model.pixel.Pixel;

/**
 * This class is used to convert a Collection<Pixel>,
 * in a gridPane that represent the Collection in input.
 */
public class PixelsParser implements Function<Collection<Pixel>, GridPane> {
    @Override
    public GridPane apply(final Collection<Pixel> pixels) {
        final var gridPane = new GridPane();
        for (Pixel p : pixels) {
            gridPane.add(new Button(), p.getPosition().getX(), p.getPosition().getY());
            gridPane.getChildren().get(gridPane.getChildren().size() - 1).setStyle(parseColor(p.getColor()));
        }
        return gridPane;
    }

    public String parseColor(final Color color) {
        return "".concat(FX_BACKGROUND_COLOR_START).concat(color.toString().replace("0x", "#")).concat(";").concat(FX_BORDER_COLOR).concat(";").concat(FX_BORDER_WIDTH);
    }
}
