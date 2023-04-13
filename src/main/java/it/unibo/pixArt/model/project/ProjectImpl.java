package it.unibo.pixArt.model.project;

public class ProjectImpl implements Project {
    private String projectName;
    private String path;
    private String coverImage;
    

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoverImage'");
    }

    @Override
    public void setCoverImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCoverImage'");
    }
    
}
