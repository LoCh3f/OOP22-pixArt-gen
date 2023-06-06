package it.unibo.pixArt.utilities.parser;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.Node;
import javafx.scene.control.Button;
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

        return gridPane.getChildren().stream().filter(b -> b instanceof Button)
                .map(b -> new PixelBuilder.PxlBuilder()
                .setX(GridPane.getColumnIndex(b))
                .setY(GridPane.getRowIndex(b))
                .setColor(parseButtonBackGround(b.getStyle()))
                .build()).collect(Collectors.toCollection(HashSet::new));
    }

    private Color parseButtonBackGround(final String style) {
        return  Color.web(style.replace(FX_BACKGROUND_COLOR_START,"")
                .replace(";","")
                .replace(FX_BORDER_COLOR,"")
                .replace(FX_BORDER_WIDTH,""));
    }
}
