package it.unibo.pixArt.model.framestate;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.model.pixel.PixelBuilder;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FrameStateTest {
    private static final int SIZE = 16;
    private Matrix grid = new PixelMatrix.MatrixBuilder().setColumns(SIZE).setRows(SIZE).build();
    private FrameState memento = new FrameStateImpl();


    @Test
    void singleFrameStateTest() {
        //Sets initial state to white and checks if all the pixels are white.
        this.memento.setState(createSet(Color.WHITE));
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).collect(Collectors.toList()), this.createSet(Color.WHITE).stream().map(e -> e.getColor()).collect(Collectors.toList()));

        //Sets the next state to Red, and checks if all the pixels are red.
        this.memento.setState(createSet(Color.RED));
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).collect(Collectors.toList()), this.createSet(Color.RED).stream().map(e -> e.getColor()).collect(Collectors.toList()));

        //Reverts back to previous state, thus controls that all the pixels are white.
        this.memento.getState();
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).collect(Collectors.toList()), this.createSet(Color.WHITE).stream().map(e -> e.getColor()).collect(Collectors.toList()));

        this.memento.setState(createSet(Color.BLUE));
        assertEquals(this.memento.peekState().stream().map(e -> e.getColor()).collect(Collectors.toList()), this.createSet(Color.BLUE).stream().map(e -> e.getColor()).collect(Collectors.toList()));
    }


    @Test
    void frameStateWithinGridTest() {
        //La grid è bianca e il memento vuoto
        assertEquals(createSet(Color.WHITE).stream().map(e -> e.getColor()).collect(Collectors.toList()), grid.getPixels().stream().map(e -> e.getColor()).collect(Collectors.toList()));
        assertEquals(0, grid.getMemento().size());

        //Salvo lo state precedente(set bianco) e faccio la grid rossa
        grid.getMemento().setState(grid.getPixels());
        grid.setPixel(createSet(Color.RED));
        assertEquals(createSet(Color.RED).stream().map(e -> e.getColor()).collect(Collectors.toList()), grid.getPixels().stream().map(e -> e.getColor()).collect(Collectors.toList()));
        assertEquals(createSet(Color.WHITE).stream().map(e -> e.getColor()).collect(Collectors.toList()), grid.getMemento().peekState().stream().map(e -> e.getColor()).collect(Collectors.toList()));
        assertEquals(1, grid.getMemento().size());

        //Ritorno allo stato precedente
        grid.revert();
        assertEquals(createSet(Color.WHITE).stream().map(e -> e.getColor()).collect(Collectors.toList()), grid.getPixels().stream().map(e -> e.getColor()).collect(Collectors.toList()));
        assertEquals(0, grid.getMemento().size());

    }

    private Set<Pixel> createSet(final Color color) {
        Set<Pixel> newSet = new HashSet<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                final Pixel p = new PixelBuilder.PxlBuilder().setY(i).setX(j).build();
                p.setColor(color);
                newSet.add(p);
            }
        }
        return newSet;
    }
}
