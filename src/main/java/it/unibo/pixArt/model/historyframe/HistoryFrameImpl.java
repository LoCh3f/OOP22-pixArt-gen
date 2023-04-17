package it.unibo.pixArt.model.historyframe;

public class HistoryFrameImpl implements HistoryFrame {
    private String imagePath;
    private int animationDuration;

    public HistoryFrameImpl(final String path) {
        this.imagePath = path;
        this.animationDuration = 100;
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
