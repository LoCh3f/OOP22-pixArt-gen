package it.unibo.pixArt.view.pages;

import it.unibo.pixArt.controller.Controller;
import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.controller.animation.AnimationControllerImpl;
import it.unibo.pixArt.controller.game.GameControllerImpl;
import it.unibo.pixArt.controller.game.GameSetupControllerImpl;
import it.unibo.pixArt.controller.login.LoginControllerImpl;
import it.unibo.pixArt.controller.project.ProjectControllerImpl;
import it.unibo.pixArt.controller.settings.SettingsControllerImpl;
import it.unibo.pixArt.controller.workspace.WorkSpaceControllerImpl;

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
    public Controller getController() {
        return this.controller;
    }
}
