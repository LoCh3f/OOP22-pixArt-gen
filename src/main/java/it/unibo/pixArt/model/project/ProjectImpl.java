package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;
import java.util.List;

public class ProjectImpl implements Project {
    private String projectName;
    private String path;
    private String fileType;
    private List<PixelGrid> frames;
    


    public ProjectImpl(final String projectName, final String path, final String fileType, final List<PixelGrid> frames) {
        this.projectName = projectName;
        this.path = path;
        this.fileType = fileType;
        this.frames = frames;
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
        this.frames.add(this.frames.size() - 1, new PixelMatrix.MatrixBuilder()
                                            .setColumns(getAllFrames().get(0).getColumns())
                                            .setRows(getAllFrames().get(0).getRows()).build());
    }

    public static class Builder {
        protected String projectName;
        protected String path;
        protected String fileType = FileTypes.PNG.getType();

        public Builder projectName(final String name) {
            this.projectName = name;
            return this;
        }

        public Builder path(final String path) {
            this.path = path;
            return this;
        }

        public Builder fileType(final FileTypes fileType) {
            this.fileType = fileType.getType();
            return this;
        }

    }

}
