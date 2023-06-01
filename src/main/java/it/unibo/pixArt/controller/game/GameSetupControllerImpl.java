package it.unibo.pixArt.controller.game;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.game.Game;
import it.unibo.pixArt.model.game.GameImpl;
import it.unibo.pixArt.model.game.GameLevels;
import it.unibo.pixArt.model.game.GameType;
import it.unibo.pixArt.model.game.builder.GameBuilderImpl;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.timer.GameTimerImpl;
import it.unibo.pixArt.model.timer.TimerType;
import it.unibo.pixArt.utilities.FileHandler;

public class GameSetupControllerImpl extends SimpleController implements GameSetupController {
    
    private List<Project> projects;

    @Override
    public void setProjects() {
        this.projects = GameLevels.getAllLevels().stream().
        map(e -> {
            try {
                return FileHandler.getInstance().fromJsonToProject(new File(e.getPathToFile()));
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            return null;
        })
        .collect(Collectors.toList());
    }

    @Override
    public List<String> getProjects() {
       return GameLevels.getAllLevels().stream().map(e -> e.getPathToImg()).collect(Collectors.toList());
    }

    @Override
    public List<TimerType> getTimers() {
        return TimerType.getAllTypes();
    }

    @Override
    public void setTimer(final String timer) {
        getModel().setTimer(new GameTimerImpl(TimerType.getAllTypes().stream()
                                                                    .filter(e -> e.getDescription() == timer)
                                                                    .findAny().get().getTime()));
    }

    @Override
    public void setProject(final int project) {
        getModel().setProject(this.projects.get(project));
    }

    @Override
    public List<GameType> getGameTypes() {
        return GameType.getGameTypes();
    }

    @Override
    public void setGameType(String type) {
        
    }
    
}
