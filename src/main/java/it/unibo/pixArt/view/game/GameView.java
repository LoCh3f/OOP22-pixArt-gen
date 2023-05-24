package it.unibo.pixArt.view.game;

import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.fxml.FXML;

public class GameView extends AbstractFXView{

    @FXML
    public void onMenuClick(){
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, this.getController().getModel());
    }

    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }
    
}
