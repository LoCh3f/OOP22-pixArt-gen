package it.unibo.pixart.model.project;

import java.util.LinkedList;
import java.util.List;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.pixart.model.grid.Matrix;
import it.unibo.pixart.model.grid.PixelMatrix;
import it.unibo.pixart.model.historyframe.HistoryFrame;
import it.unibo.pixart.model.historyframe.HistoryFrameImpl;

/**
 * Implementation of Project interface.
 */
public final class ProjectImpl implements Project {
    private final String projectName;
    private final String path;
    private final String fileType;
    private final List<Matrix> frames;
    private final List<HistoryFrame> historyFrames = new LinkedList<>();

    /**
     * Constructor for ProjectImpl.
     * @param projectName
     * @param path
     * @param fileType
     * @param frames
     */
    @SuppressFBWarnings
    public ProjectImpl(final String projectName, final String path, final String fileType, final List<Matrix> frames) {
        this.projectName = projectName;
        this.path = path;
        this.fileType = fileType;
        this.frames = frames;
        this.historyFrames.add(new HistoryFrameImpl(0));
    }

    @Override
    public String getName() {
        return this.projectName;
    }

    @Override
    public String getPath() {
        return this.path;
    }

    @SuppressFBWarnings
    @Override
    public List<Matrix> getAllFrames() {
        return this.frames;
    }

    @SuppressFBWarnings
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

    @Override
    public void addNewHistoryFrame(final int index) {
        this.historyFrames.add(new HistoryFrameImpl(index));
    }

    @Override
    public HistoryFrame getLastHistoryFrame() {
        return this.getAllHistoryFrames().get(getAllHistoryFrames().size() - 1);
    }

}
