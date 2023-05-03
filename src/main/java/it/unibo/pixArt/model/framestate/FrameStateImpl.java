package it.unibo.pixArt.model.framestate;

import java.util.List;
import java.util.Set;
import java.util.Stack;

import it.unibo.pixArt.model.pixel.Pixel;

public class FrameStateImpl implements FrameState {
    private Stack<Set<Pixel>> stateStack = new Stack<>();

    public FrameStateImpl() {

    }
    
    @Override
    public void setState(final Set<Pixel> snapshot) {
       this.stateStack.push(snapshot);
    }

    @Override
    public Set<Pixel> getState() {
        return this.stateStack.pop();
    }
    
}
