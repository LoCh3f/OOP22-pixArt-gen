package it.unibo.pixart.model.colorstack;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import it.unibo.pixart.model.grid.Matrix;
import it.unibo.pixart.model.grid.PixelMatrix;
import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.model.tool.AbstractTool;
import it.unibo.pixart.model.tool.ToolFactory;
import it.unibo.pixart.model.tool.ToolFactoryImpl;
import it.unibo.pixart.utilities.MatrixConverter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

/** The Test class for the ColorStack class.
 */
class ColorStackTest {
    private final Matrix grid = new PixelMatrix.MatrixBuilder().setColumns(16).setRows(16).build();
    private final MatrixConverter converter = new MatrixConverter();
    private ColorStack stack;
    private final ToolFactory toolFatory = new ToolFactoryImpl();
    private final AbstractTool tool = toolFatory.createTool("BUCKET", Color.BLACK, 1);
    private static final int PIXELNUMBER = 256;
    private static final int PIXELNUMBER2 = 255;

    /*Initially the grid is made up completelly of white pixels. Thus, we check that the stack's map
     * has only one entryset: K->Color.WHITE, V->256 white pixels.
     */
    @Test
    void checkOne() {
        final ColorStack stack = new ColorStackImpl(new MatrixConverter().apply(grid.getPixels()), grid.getPixels().size());
        assertEquals(1, stack.getColorMap().entrySet().stream().filter(e -> e.getKey() == Color.WHITE).count());
        assertEquals(PIXELNUMBER, stack.getColorMap().entrySet().stream().mapToInt(e -> e.getValue().size()).sum());
    }

    /*Now, using a bucket tool we color the grid in black, and check that the stack's map
     * has only one entryset: K->Color.BLACK, V->256 vlack pixels.
     */
    @Test
    void checkTwo() {
        this.grid.setPixel(this.tool.updateGrid(this.grid.getPixels().iterator().next(), this.grid.getPixels()));
        this.stack = new ColorStackImpl(new MatrixConverter().apply(this.grid.getPixels()), grid.getPixels().size());
        assertEquals(1, stack.getColorMap().entrySet().stream().filter(e -> e.getKey() == Color.BLACK).count());
        assertEquals(PIXELNUMBER, stack.getColorMap().values().stream().flatMap(Set::stream).count());
    }

    /*Now we'll remove a pixel from a set, and check that the total number of pixels has decreased. */
    @Test
    void checkRemovePixel() {
        final Pixel p = grid.getPixels().stream().collect(Collectors.toList()).get(0);
        this.stack = new ColorStackImpl(this.converter.apply(grid.getPixels()), grid.getPixels().size());
        this.stack.removePixel(Color.WHITE, p);
        assertEquals(1, stack.getColorMap().entrySet().stream().filter(e -> e.getKey() == Color.WHITE).count());
        assertEquals(PIXELNUMBER2, stack.getColorMap().entrySet().stream().mapToInt(e -> e.getValue().size()).sum());
    }

    /*Test to check weather the method isEmpty works if all the pixels get removed. */
    @Test
    void checkEntrySetRemoved() {
        this.stack = new ColorStackImpl(this.converter.apply(grid.getPixels()), grid.getPixels().size());
        assertFalse(this.stack.isEmpty());
        for (final var elem : this.grid.getPixels()) {
            this.stack.removePixel(elem.getColor(), elem);
        }
        assertTrue(this.stack.isEmpty());
    }

    /*Test to check if checkPercentage works */
    @Test
    void checkPercentage() {
        this.stack = new ColorStackImpl(this.converter.apply(grid.getPixels()), grid.getPixels().size());
        assertTrue(this.stack.getPercentage() < 1);
        this.grid.getPixels().stream().forEach(e -> this.stack.removePixel(e.getColor(), e));
        assertEquals(100, this.stack.getPercentage());
    }
}
