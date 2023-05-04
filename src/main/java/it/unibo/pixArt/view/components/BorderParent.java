package it.unibo.pixArt.view.components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BorderParent implements ParentGetter {
    private final BorderPane root = new BorderPane();

    private BorderParent() {

    }

    @Override
    public Parent get() {
        return this.root;
    }

    public static class Builder {
        private Node right = null;

        private Node left = null;

        private Node bottom = null;

        private Node top = null;

        public Builder setLeft(final Node left) {
            this.left = left;
            return this;
        }

        public Builder setRight(final Node right) {
            this.right = right;
            return this;
        }

        public Builder setBottom(final Node bottom) {
            this.bottom = bottom;
            return this;
        }

        public Builder setTop(final Node top) {
            this.top = top;
            return this;
        }

        public BorderParent build() {
            return new BorderParent();
        }

    }
}
