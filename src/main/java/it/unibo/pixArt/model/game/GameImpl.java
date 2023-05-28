package it.unibo.pixArt.model.game;

import it.unibo.pixArt.model.project.Project;

public class GameImpl implements Game {
    private GameType gameType;
    private Project testProject;
    
    @Override
    public Project getTestProject() {
        return this.testProject;
    }

    @Override
    public GameType getGameType() {
        return this.gameType;
    }
    
}
