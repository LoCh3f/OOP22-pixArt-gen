package it.unibo.pixart.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * This class is used to create a button with a specific style, text, and event handler.
 */
public final class ButtonSetter extends Button {

    /**
     * @param text            the text of the button
     * @param backgroundStyle the style of the button
     * @param eventH          the event handler of the button
     */
    private ButtonSetter(final String text, final String backgroundStyle, final EventHandler<ActionEvent> eventH) {
        super();
        this.setStyle(backgroundStyle);
        this.setText(text);
        this.setOnAction(eventH);
    }

    /**
     * Builder class for ButtonSetter.
     */
    public static final class Builder {
        private String text;

        private String backgroundStyle;

        private EventHandler<ActionEvent> eventH;

        /**
         * @param text the text of the button
         * @return the builder
         */
        public Builder setText(final String text) {
            this.text = text;
            return this;
        }

        /**
         * @param backgroundStyle the style of the button
         * @return the builder
         */
        public Builder setBackground(final String backgroundStyle) {
            this.backgroundStyle = backgroundStyle;
            return this;
        }

        /**
         * @param eventH the event handler of the button
         * @return the builder
         */
        public Builder setEventH(final EventHandler<ActionEvent> eventH) {
            this.eventH = eventH;
            return this;
        }

        /**
         * @return the button
         */
        public ButtonSetter build() {
            return new ButtonSetter(this.text, this.backgroundStyle, this.eventH);
        }

    }
}
