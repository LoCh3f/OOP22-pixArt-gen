package it.unibo.pixart.view.pages;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.pixart.controller.Controller;
import it.unibo.pixart.controller.SimpleController;
import it.unibo.pixart.controller.animation.AnimationControllerImpl;
import it.unibo.pixart.controller.game.GameControllerImpl;
import it.unibo.pixart.controller.game.GameSetupControllerImpl;
import it.unibo.pixart.controller.login.LoginControllerImpl;
import it.unibo.pixart.controller.project.ProjectControllerImpl;
import it.unibo.pixart.controller.settings.SettingsControllerImpl;
import it.unibo.pixart.controller.workspace.WorkSpaceControllerImpl;

/**
 * Enum containing all availbale pages.
 */
public enum Pages {
    /**
     * menu view.
     */
    MENU("menu", new SimpleController()),

    /**
     * workspace view.
     */
    WORKSPACE("workspace", new WorkSpaceControllerImpl()),

    /**
     * animation view.
     */
    ANIMATION("animation", new AnimationControllerImpl()),

    /**
     * history view.
     */
    HISTORY("projects", new ProjectControllerImpl()),

    /**
     * Settings view.
     */
    SETTINGS("settings", new SettingsControllerImpl()),

    /**
     * Login view.
     */
    LOGIN("login", new LoginControllerImpl()),

    /**
     * GameSetup view.
     */
    GAMESETUP("gamesetup", new GameSetupControllerImpl()),

    /**
     * Game view.
     */
    GAMEVIEW("game", new GameControllerImpl());
    private final String name;

    private final Controller controller;

    /**
     * @param name       of the view.
     * @param controller the associated to the view.
     */
    Pages(final String name, final Controller controller) {
        this.name = name;
        this.controller = controller;
    }

    /**
     * @return the name of the view.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the controller of the view.
     */
    @SuppressFBWarnings
    public Controller getController() {
        return this.controller;
    }
}
