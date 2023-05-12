package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.historyframe.HistoryFrame;
import it.unibo.pixArt.model.historyframe.HistoryFrameImpl;

import java.util.LinkedList;
import java.util.List;

public class ProjectImpl implements Project {
    private String projectName;
    private String path;
    private String fileType;
    private List<PixelGrid> frames;
    private List<HistoryFrame> historyFrames;

    public ProjectImpl(final String projectName, final String path, final String fileType, final List<PixelGrid> frames) {
        this.projectName = projectName;
        this.path = path;
        this.fileType = fileType;
        this.frames = frames;
        this.historyFrames = new LinkedList<>();
        this.historyFrames.add(new HistoryFrameImpl("/image/toad.png"));
    }

    @Override
    public String getName() {
        return this.projectName;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @Override
    public List<PixelGrid> getAllFrames() {
        return this.frames;
    }

    @Override
    public List<HistoryFrame> getAllHistoryFrames() {
        return this.historyFrames;
    }

    @Override
    public String toString() {
        return "Project name:\t" + this.projectName
        + "\n" + "Path name:\t" + this.path
        + "\n" + "File type:\t" + this.fileType;
    }

    @Override
    public String getFileType() {
        return this.fileType;
    }

    @Override
    public void addNewFrame() {
        this.frames.add(new PixelMatrix.MatrixBuilder()
                        .setColumns(getAllFrames().get(0).getColumns())
                        .setRows(getAllFrames().get(0).getRows()).build());
    }

}
