package it.unibo.pixart.utilities.parser;

import it.unibo.pixart.model.pixel.Pixel;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.function.Function;

//CHECKSTYLE: AvoidStarImport OFF
import static it.unibo.pixart.utilities.variables.FXViewVariables.*;

/**
 * This class is used to convert a Collection<Pixel>,
 * in a gridPane that represent the Collection in input.
 */
public final class PixelsParser implements Function<Collection<Pixel>, GridPane> {
    /**
     * @param pixels the function argument (Collection<Pixel>)
     * @return a GridPane that represent the Collection in input
     */
    @Override
    public GridPane apply(final Collection<Pixel> pixels) {
        final var gridPane = new GridPane();
        for (final Pixel p : pixels) {
            gridPane.add(new Button(), p.getPosition().getX(), p.getPosition().getY());
            gridPane.getChildren().get(gridPane.getChildren().size() - 1).setStyle(parseColor(p.getColor()));
        }
        return gridPane;
    }

    /**
     * @param color the color to parse
     * @return a String that represent the color in input for set the style of the button
     */
    public String parseColor(final Color color) {
        return "".concat(FX_BACKGROUND_COLOR_START)
                .concat(color.toString().replace("0x", "#"))
                .concat(";").concat(FX_BORDER_COLOR).concat(";")
                .concat(FX_BORDER_WIDTH);
    }
}
