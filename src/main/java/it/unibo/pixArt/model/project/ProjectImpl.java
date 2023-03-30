package it.unibo.pixArt.model.project;

public class ProjectImpl implements Project {
    private String projectName;

    @Override
    public void setName(String newName) {
        this.projectName = newName;
    }

    @Override
    public String getName() {
        return this.projectName;
    }
    
}
