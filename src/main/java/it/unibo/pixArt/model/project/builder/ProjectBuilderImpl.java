package it.unibo.pixArt.model.project.builder;

import it.unibo.pixArt.model.grid.Matrix;
import it.unibo.pixArt.model.grid.PixelMatrix;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.project.ProjectImpl;

import java.util.LinkedList;
import java.util.List;

/**
 * Implements ProjectBuilder interface..
 */
public final class ProjectBuilderImpl implements ProjectBuilder {
    private String projectName;
    private String path;
    private String fileType;
    private List<Matrix> frames;

    @Override
    public ProjectBuilder projectName(final String projectName) {
        this.projectName = projectName;
        return this;
    }

    @Override
    public ProjectBuilder path(final String path) {
        this.path = path;
        return this;
    }

    @Override
    public ProjectBuilder fileType(final String fileType) {
        this.fileType = fileType;
        return this;
    }

    @Override
    public ProjectBuilder frames(final int size) {
        this.frames = new LinkedList<>();
        /*Build the first first grid */
        this.frames.add(0, new PixelMatrix.MatrixBuilder().setColumns(size).setRows(size).build());
        return this;
    }

    @Override
    public Project build() {
        return new ProjectImpl(this.projectName, this.path, this.fileType, this.frames);
    }


}
