package it.unibo.pixArt.model.project;

import it.unibo.pixArt.model.grid.PixelGrid;

import java.util.List;

public class ProjectImpl implements Project {
    private String projectName;
    private String path;
    private String coverImage;
    private List<PixelGrid> frames;

    public ProjectImpl() {

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
    public String getCoverImage() {
        return this.coverImage;
    }

    @Override
    public void setCoverImage(final String path) {
        this.coverImage = path;
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

}
