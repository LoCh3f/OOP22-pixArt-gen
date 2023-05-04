package it.unibo.pixArt.view.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ButtonSetter extends Button {

    private ButtonSetter(final String text, final String backgroundStyle, final EventHandler<ActionEvent> eventH) {
        super();
        this.setStyle(backgroundStyle);
        this.setText(text);
        this.setOnAction(eventH);
    }

    public static class Builder {
        private String text;

        private String backgroundStyle;

        private EventHandler<ActionEvent> eventH;

        public Builder setText(final String text) {
            this.text = text;
            return this;
        }

        public Builder setBackground(final String backgroundStyle) {
            this.backgroundStyle = backgroundStyle;
            return this;
        }

        public Builder setEventH(final EventHandler<ActionEvent> eventH) {
            this.eventH = eventH;
            return this;
        }

        public ButtonSetter build() {
            return new ButtonSetter(this.text, this.backgroundStyle, this.eventH);
        }

    }
}
