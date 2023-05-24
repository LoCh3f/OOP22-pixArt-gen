package it.unibo.pixArt.view.impl;

import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;

public class GameSetupView extends AbstractFXView {

    @FXML
    private ListView projectsList;

    @FXML
    private ChoiceBox timerChoiceBox;

    @FXML
    private void onStartClicked() {
        //
    }

    @FXML
    private void onDiscardClicked() {
        PageLoader.getInstance().switchPage(this.getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
    
}
