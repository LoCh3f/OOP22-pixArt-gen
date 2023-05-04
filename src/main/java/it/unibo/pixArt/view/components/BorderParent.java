package it.unibo.pixArt.view.components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;

public class BorderParent implements ParentGetter {
    private final Parent root;

    private BorderParent(final Node right, final Node left,
                         final Node bottom, final Node top,
                         final Node center) {
        final var pane = new BorderPane();
        pane.setCenter(center);
        pane.setBottom(bottom);
        pane.setTop(top);
        pane.setLeft(left);
        pane.setRight(right);
        this.root = pane;
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

        private Node center = null;

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
            return new BorderParent(this.right,this.left,this.bottom,this.top,this.center);
        }

    }
}
