package it.unibo.pixArt.model.framestate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.pixel.ImplPixel;
import it.unibo.pixArt.model.pixel.Pixel;
import javafx.scene.paint.Color;

public class FrameStateTest {
    private static final int SIZE = 16;
    private PixelGrid grid;
    private FrameState memento = new FrameStateImpl();


    @Test
    void singleFrameStateTest() {
        //Sets initial state to white and checks if all the pixels are white.
        this.memento.setState(createSet(Color.WHITE));
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).toList(),this.createSet(Color.WHITE).stream().map(e -> e.getColor()).toList());

        //Sets the next state to Red, and checks if all the pixels are red.
        this.memento.setState(createSet(Color.RED));
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).toList(),this.createSet(Color.RED).stream().map(e -> e.getColor()).toList());

        //Reverts back to previous state, thus controls that all the pixels are white
        this.memento.getState();
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).toList(),this.createSet(Color.WHITE).stream().map(e -> e.getColor()).toList());
    }

    
    @Test
    void frameStateWithinGridTest() {
       //To be done.
    }

    private Set<Pixel> createSet(final Color color) {
        Set<Pixel> newSet = new HashSet<>();
        for(int i = 0; i < SIZE; i++) {
            for(int j = 0; j < SIZE; j++) {
                final Pixel p = new ImplPixel(j, i);
                p.setColor(color);
                newSet.add(p);
            }
        }
        return newSet;
    }
}
