package it.unibo.pixart.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuItemBuilder extends MenuItem {


    private MenuItemBuilder(final EventHandler<ActionEvent> e, final String name) {
        super(name);
        this.setOnAction(e);
    }

    public static class Builder {

        private String name;

        private EventHandler<ActionEvent> e = null;

        public Builder setEventH(final EventHandler<ActionEvent> e) {
            this.e = e;
            return this;
        }

        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        public MenuItemBuilder build() throws IllegalStateException {
            if (this.e == null) {
                throw new IllegalStateException("EventHandler not set");
            }
            return new MenuItemBuilder(this.e, this.name);
        }


    }


}
