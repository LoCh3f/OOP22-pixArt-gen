package it.unibo.pixArt.model.historyframe;

public class HistoryFrameImpl implements HistoryFrame {
    private static final int DEF_DELAY = 100;
    private String imagePath;
    private int animationDuration;

    public HistoryFrameImpl(final String path) {
        this.imagePath = path;
        this.animationDuration = DEF_DELAY;
    }

    @Override
    public String getPath() {
        return this.imagePath;
    }

    @Override
    public void setPath(String path) {
       this.imagePath = path;
    }

    @Override
    public void setAnimationDuration(int duration) {
        this.animationDuration = duration;
    }

    @Override
    public int getAnimationDuration() {
        return this.animationDuration;
    }
    
}
