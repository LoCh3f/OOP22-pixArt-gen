package it.unibo.pixArt.view.impl;

import it.unibo.pixArt.controller.settings.SettingsController;
import it.unibo.pixArt.view.AbstractFXView;

public class SettingsView extends AbstractFXView {

    @Override
    public void init() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'init'");
    }

    private SettingsController getSettingsController() {
        return (SettingsController) this.getController();
    }
    
}
