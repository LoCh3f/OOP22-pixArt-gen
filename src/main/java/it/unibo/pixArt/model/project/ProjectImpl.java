package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.PixelGrid;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.grid.PixelMatrix.MatrixBuilder;

import java.util.LinkedList;
import java.util.List;

public class ProjectImpl implements Project {
    private String projectName;
    private String path;
    private String fileType;
    private int size;
    private List<PixelGrid> frames;

    public ProjectImpl(String projectName,String path,String fileType, int size) {
        this.projectName = projectName;
        this.path = path;
        this.fileType = fileType;
        this.size = size;
        this.frames = new LinkedList<>();
        /*Build the first first grid */
        this.frames.add(0, new PixelMatrix.MatrixBuilder().setColumns(size).setRows(size).build());
    }

    @Override
    public void setName(String newName) {
        this.projectName = newName;
    }

    @Override
    public String getName() {
        return this.projectName;
    }

    @Override
    public void setPath(String path) {
        this.path = path;
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
        + "\n" + "File type:\t" + this.fileType
        + "\n" + "Size\t" + this.size;
    }
    
    @Override
    public Integer getSize() {
        return this.size;
    }

    @Override
    public String getFileType() {
        return this.fileType;
    }

    @Override
    public void addNewFrame() {
        this.frames.add(this.frames.size() - 1, new PixelMatrix.MatrixBuilder().setColumns(size).setRows(size).build());
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
