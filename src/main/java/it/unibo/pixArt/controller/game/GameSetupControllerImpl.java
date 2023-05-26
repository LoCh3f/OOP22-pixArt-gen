package it.unibo.pixArt.controller.game;

import java.util.LinkedList;
import java.util.List;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.project.Project;
import it.unibo.pixArt.model.timer.GameTimerImpl;
import it.unibo.pixArt.model.timer.TimerType;

public class GameSetupControllerImpl extends SimpleController implements GameSetupController {
    
    private static String GAMES_PATH = "/games";
    private List<Project> projects;

    @Override
    public void setProjects() {
        this.projects = new LinkedList<>();
    }

    @Override
    public List<String> getProjects() {
        return List.of();
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
    
}
