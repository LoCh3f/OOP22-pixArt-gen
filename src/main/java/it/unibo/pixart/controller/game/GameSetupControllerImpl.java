package it.unibo.pixart.controller.game;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import it.unibo.pixart.controller.SimpleController;
import it.unibo.pixart.model.game.GameLevels;
import it.unibo.pixart.model.game.GameType;
import it.unibo.pixart.model.game.builder.GameBuilderImpl;
import it.unibo.pixart.model.project.Project;
import it.unibo.pixart.model.timer.GameTimer;
import it.unibo.pixart.model.timer.GameTimerImpl;
import it.unibo.pixart.model.timer.TimerType;
import it.unibo.pixart.utilities.FileHandler;

/**
 * Implementation for GameSetupController.
 */
public final class GameSetupControllerImpl extends SimpleController implements GameSetupController {

    private List<Project> projects;
    private GameTimer gameTimer;
    private GameType gameType;
    private int selectedProject;

    @SuppressWarnings("PMD.AvoidPrintStackTrace")
    @Override
    public void setProjects() {
        this.projects = GameLevels.getAllLevels().stream().
        map(e -> {
            try {
                return FileHandler.getInstance().fromJsonToProject(new File(e.getPathToFile()));
            } catch (IOException e1) {
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
        this.gameTimer = new GameTimerImpl(TimerType.getAllTypes().stream()
                                                                    .filter(e -> e.getDescription() == timer)
                                                                    .findAny().get().getTime());
    }

    @Override
    public void setProject(final int project) {
        this.selectedProject = project;
    }

    @Override
    public List<GameType> getGameTypes() {
        return GameType.getGameTypes();
    }

    @Override
    public void setGameType(final String type) {
        this.gameType = GameType.getGameTypes().stream().filter(e -> e.getName() == type).findAny().get();
    }

    @Override
    public void setGame() {
        getModel().setGame(new GameBuilderImpl().gameTimer(this.gameTimer).gameType(this.gameType).build());
        getModel().setProject(this.projects.get(this.selectedProject));
    }
}
