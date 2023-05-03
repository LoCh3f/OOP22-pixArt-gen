package it.unibo.pixArt.view.workspace;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class MenuItemBuilder {

    private MenuItem b;

    private MenuItemBuilder(final EventHandler<ActionEvent> e, final String name) {
        this.b = new MenuItem(name);
        this.b.setOnAction(e);
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

        public MenuItemBuilder build() {
            return new MenuItemBuilder(this.e, this.name);
        }


    }

    public MenuItem get() {
        return this.b;
    }


}
