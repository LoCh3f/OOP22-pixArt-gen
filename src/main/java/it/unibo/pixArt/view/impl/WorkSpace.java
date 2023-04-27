package it.unibo.pixArt.view.impl;

import java.util.Set;

import it.unibo.pixArt.model.ModelImpl;
import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.view.AbstractFXView;
import it.unibo.pixArt.view.SimpleView;
import it.unibo.pixArt.view.pages.PageLoader;
import it.unibo.pixArt.view.pages.Pages;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class WorkSpace extends AbstractFXView {

    @Override
    public void init() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    @FXML
    public void onMenuClicked(final ActionEvent event) {
        PageLoader.getInstance().switchPage(getStage(), Pages.MENU, new ModelImpl(null, null, null));
    }

    private void clicked(ActionEvent e) {
        //prendi il pulsante cliccato...
    }

    public void updateView(Set<Pixel> pixels){
        //loop 
    }
}
