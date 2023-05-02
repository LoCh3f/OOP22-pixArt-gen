package it.unibo.pixArt.view.workspace;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

public class ButtonBuilder {

    private final MenuItem b = new MenuItem();

    private ButtonBuilder(final EventHandler<ActionEvent> e) {
        this.b.setOnAction(e);
    }

    public static class Builder {

        private EventHandler<ActionEvent> e = null;

        public Builder setEventH(final EventHandler<ActionEvent> e) {
            this.e = e;
            return this;
        }

        public ButtonBuilder build() {
            return new ButtonBuilder(this.e);
        }


    }

    public MenuItem get() {
        return this.b;
    }


}
