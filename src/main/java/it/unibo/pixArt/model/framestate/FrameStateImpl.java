package it.unibo.pixArt.model.framestate;

import java.util.EmptyStackException;
import java.util.Set;
import java.util.Stack;

import it.unibo.pixArt.model.pixel.Pixel;
/**
 * Implementation of FrameState.
 */
public final class FrameStateImpl implements FrameState {
    private Stack<Set<Pixel>> stateStack = new Stack<>();

    /**
     * Constructor for FrameStateImpl.
     */
    public FrameStateImpl() {

    }

    @Override
    public void setState(final Set<Pixel> newState) {
       this.stateStack.push(newState);
    }

    @Override
    public Set<Pixel> getState() {
        try {
            return this.stateStack.pop(); 
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public Set<Pixel> peekState() {
        try {
            return this.stateStack.peek(); 
        } catch (EmptyStackException e) {
            throw new EmptyStackException();
        }
    }

    @Override
    public int size() {
        return stateStack.size();
    }

    @Override
    public void emptyStack() {
        this.stateStack = new Stack<>();
    }

}
