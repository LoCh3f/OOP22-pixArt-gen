package it.unibo.pixArt.utilities.parser;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;

/**
 * This Class help to convert a GridPane in a Collection of Pixel.
 */
public class GridPaneParser implements Function<GridPane, Collection<Pixel>> {
    @Override
    public Collection<Pixel> apply(final GridPane gridPane) {
        final var result = new HashSet<Pixel>();
        System.out.println(gridPane.getChildren().size());

        return result;
    }

    private Color parseButtonBackGround(final String style) {
        System.out.println(style.replace(FX_BACKGROUND_COLOR_START,"").replace("#","0x")
                .replace(";","")
                .replace(FX_BORDER_COLOR,"")
                .replace(FX_BORDER_WIDTH,""));
        return  Color.web("white");
    }
}
