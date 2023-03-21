package it.unibo.pixArt.view;

import it.unibo.pixArt.controller.Controller;

public class SimpleView implements View {

    private Controller controller;


    @Override
    public Controller getController() {
        return this.controller;
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
}