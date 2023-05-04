package it.unibo.pixArt.view.components;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public interface StageDistribution {

    class ParallelStage extends Stage {
        public static final Double SIZE = 600.0;

        public ParallelStage(final Parent root, final String title, final Image icon) {
            super();
            setTitle(title);
            getIcons().add(icon);
            setScene(new Scene(root));
        }
    }
}
