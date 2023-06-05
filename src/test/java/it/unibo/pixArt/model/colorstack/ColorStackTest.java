package it.unibo.pixArt.model.colorstack;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.tool.AbstractTool;
import it.unibo.pixArt.model.tool.ToolFactory;
import it.unibo.pixArt.model.tool.ToolFactoryImpl;
import it.unibo.pixArt.utilities.MatrixConverter;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

public class ColorStackTest {
    private final Matrix grid = new PixelMatrix.MatrixBuilder().setColumns(16).setRows(16).build();
    private MatrixConverter converter = new MatrixConverter();
    private ColorStack stack; //= new ColorStackImpl(this.converter.apply(grid.getPixels()), grid.getPixels().size());
    private final ToolFactory toolFatory = new ToolFactoryImpl();
    private AbstractTool tool = toolFatory.createTool("BUCKET", Color.BLACK, 2);

    /*Initially the grid is made up completelly of white pixels. Thus, we check that the stack's map
     * has only one entryset: K->Color.WHITE, V->256 white pixels.
     */
    @Test
    void checkOne() {
        final ColorStack stack = new ColorStackImpl(new MatrixConverter().apply(grid.getPixels()),grid.getPixels().size());
        assertEquals(1, stack.getColorMap().entrySet().stream().filter(e -> e.getKey() == Color.WHITE).count());
        assertEquals(256, stack.getColorMap().entrySet().stream().mapToInt(e -> e.getValue().size()).sum());
    }

    /*Now, using a bucket tool we color the grid in black, and check that the stack's map
     * has only one entryset: K->Color.BLACK, V->256 vlack pixels.
     */
    @Test
    void checkTwo() {
        final Pixel p = grid.getPixels().stream().filter(e -> e.getPosition().getX() == 0 && e.getPosition().getY() == 0).findAny().get();
        this.grid.setPixel(this.tool.updateGrid(p, this.grid.getPixels()));
        final ColorStack stack = new ColorStackImpl(new MatrixConverter().apply(grid.getPixels()),grid.getPixels().size());
        assertEquals(1, stack.getColorMap().entrySet().stream().filter(e -> e.getKey() == Color.BLACK).count());
        assertEquals(256, stack.getColorMap().values().stream().flatMap(Set::stream).count());
    }
   
    /*Now we'll remove a pixel from a set, and check that the total number of pixels has decreased. */
    @Test
    void checkRemovePixel() {
        Pixel p = grid.getPixels().stream().toList().get(0);
        this.stack = new ColorStackImpl(this.converter.apply(grid.getPixels()), grid.getPixels().size());
        this.stack.removePixel(Color.WHITE, p);
        assertEquals(1, stack.getColorMap().entrySet().stream().filter(e -> e.getKey() == Color.WHITE).count());
        assertEquals(255, stack.getColorMap().entrySet().stream().mapToInt(e -> e.getValue().size()).sum());
    }

    /*Test to check weather the method isEmpty works if all the pixels get removed. */
    @Test
    void checkEntrySetRemoved() {
        this.stack = new ColorStackImpl(this.converter.apply(grid.getPixels()), grid.getPixels().size());
        assertTrue(!this.stack.isEmpty());
        for(var elem : this.grid.getPixels()) {
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
        assertEquals(100,this.stack.getPercentage());
    }
}
