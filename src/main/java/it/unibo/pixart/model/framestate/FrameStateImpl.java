package it.unibo.pixart.model.framestate;

import java.util.Set;
import java.util.Stack;

import it.unibo.pixart.model.pixel.Pixel;
/**
 * Implementation of FrameState.
 */
public final class FrameStateImpl implements FrameState {
    private Stack<Set<Pixel>> stateStack = new Stack<>();

    @Override
    public void setState(final Set<Pixel> newState) {
       this.stateStack.push(newState);
    }

    @SuppressWarnings("PMD.AvoidCatchingThrowable")
    @Override
    public Set<Pixel> getState() {
        return this.stateStack.pop();
    }

    @SuppressWarnings("PMD.AvoidCatchingThrowable")
    @Override
    public Set<Pixel> peekState() {
        return this.stateStack.peek();
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
