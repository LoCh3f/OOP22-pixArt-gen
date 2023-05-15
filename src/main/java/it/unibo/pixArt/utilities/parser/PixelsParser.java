package it.unibo.pixArt.utilities.parser;

import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.function.Function;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;

public class PixelsParser implements Function<Collection<Pixel>, GridPane> {
    @Override
    public GridPane apply(Collection<Pixel> pixels) {
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
