package it.unibo.pixart.view.components;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public interface StageDistribution {

    public final Double PREF_WIDTH = 900.0;
    public final Double PREF_HEIGHT = 700.0;

    class ParallelStage extends Stage {

        public ParallelStage(final Parent root, final String title, final Image icon) {
            super();
            setTitle(title);
            getIcons().add(icon);
            setScene(new Scene(root));
        }
    }
}
