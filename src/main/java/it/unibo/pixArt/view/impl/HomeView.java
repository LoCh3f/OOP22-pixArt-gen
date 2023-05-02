package it.unibo.pixArt.view.impl;

import java.util.Optional;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.animation.AnimationModelImpl;
import it.unibo.pixArt.model.workspace.WorkSpaceModelImpl;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.SimpleView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class HomeView extends AbstractFXView {

    @FXML
    public void onSetUpClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETTINGS, new ModelImpl(null, null, null));
    }
    
   @FXML
    public void onWorkSpaceClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.SETTINGS, new ModelImpl(null, null, null));
       // PageLoader.getInstance().switchPage(this.getStage(), Pages.WORKSPACE, new WorkSpaceModelImpl(this.getController().getModel().getProject()));
    }

    @FXML
    public void onProjectHistoryClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.HISTORY, new ModelImpl(null, null, null));
    }

    @FXML
    public void onAnimationClick(final ActionEvent event) {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.ANIMATION, this.getController().getModel());
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    private HomeView getHomeController() {
        return (HomeView) this.getController();
    }
}
