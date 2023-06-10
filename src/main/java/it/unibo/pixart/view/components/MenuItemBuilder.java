package it.unibo.pixart.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;

/**
 * This class is used to create a MenuItem with a specific event handler and name.
 */
public final class MenuItemBuilder extends MenuItem {


    /**
     * @param e    the event handler
     * @param name the name of the menu item
     */
    private MenuItemBuilder(final EventHandler<ActionEvent> e, final String name) {
        super(name);
        this.setOnAction(e);
    }

    /**
     * Builder class for MenuItemBuilder.
     */
    public static final class Builder {

        private String name;

        private EventHandler<ActionEvent> e;

        /**
         * @param e the event handler
         * @return the builder
         */
        public Builder setEventH(final EventHandler<ActionEvent> e) {
            this.e = e;
            return this;
        }

        /**
         * @param name the name of the menu item
         * @return the builder
         */
        public Builder setName(final String name) {
            this.name = name;
            return this;
        }

        /**
         * @return the MenuItemBuilder
         * @throws IllegalStateException if the EventHandler is not set
         */
        public MenuItemBuilder build() throws IllegalStateException {
            if (this.e == null) {
                throw new IllegalStateException("EventHandler not set");
            }
            return new MenuItemBuilder(this.e, this.name);
        }


    }


}
