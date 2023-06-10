package it.unibo.pixart.view.components;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;

/**
 * This interface is still in a primitive state.
 * I would like to use it to create multiple stages in a fastest way at the same time.
 */
public interface StageDistribution {

    /**
     * @param roots  the root of the stage;
     * @param titles the titles of the stages;
     * @param icons  the icons of the stages;
     * @param nodes  the nodes of the stages;
     * @return the set of stages.
     */
    Set<Stage> dropStages(List<Parent> roots, List<String> titles, List<Image> icons, List<Node> nodes);

    /**
     * Class that extends Stage for build more complex in a fastest way.
     */
    class ParallelStage extends Stage {

        /**
         * @param root  the root of the stage;
         * @param title the title of the stage;
         * @param icon  the icon of the stage;
         */
        public ParallelStage(final Parent root, final String title, final Image icon) {
            super();
            setTitle(title);
            getIcons().add(icon);
            setScene(new Scene(root));
        }
    }
}
