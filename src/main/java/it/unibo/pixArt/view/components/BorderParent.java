package it.unibo.pixArt.view.components;

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class BorderParent extends BorderPane {


    private BorderParent(final Node right, final Node left, final Node bottom, final Node top, final Node center) {
        super();
        this.setCenter(center);
        this.setBottom(bottom);
        this.setTop(top);
        this.setLeft(left);
        this.setRight(right);

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

        public Builder setCenter(final Node center) {
            this.center = center;
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
            return new BorderParent(this.right, this.left, this.bottom, this.top, this.center);
        }

    }
}
