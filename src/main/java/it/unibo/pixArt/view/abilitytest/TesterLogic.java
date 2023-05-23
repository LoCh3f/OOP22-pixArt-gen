package it.unibo.pixArt.view.abilitytest;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.mirror.GenericMirror;
import it.unibo.pixArt.utilities.mirror.Mirror;

import java.util.*;

import static it.unibo.pixArt.utilities.variables.FXViewVariables.*;

public class TesterLogic implements Logic {


    public static final Set<String> imagePaths = new HashSet<>(List.of(IMAGE_PATH + TOAD_IMAGE, IMAGE_PATH + SONIC_IMAGE, IMAGE_PATH + HOMER_IMAGE, IMAGE_PATH + FLOPPY_BIRD));

    private final Mirror<Pixel> gridMirror = new GenericMirror<>();

    public TesterLogic() {

    }

    @Override
    public String getImagePath() {
        return imagePaths.stream().toList().get(new Random().nextInt(imagePaths.size()));
    }

    public String test(final Collection<Pixel> userGrid) {
        return null; //templatePath(this.gridMirror.getDifference(   ,userGrid).size());
    }


    private String templatePath(final int difference) {
        return switch (difference) {
            case 0 -> IMAGE_PATH + IMAGE_VERY_GOOD;
            case 10 -> IMAGE_PATH + IMAGE_GOOD;
            case 30 -> IMAGE_PATH + IMAGE_BAD;
            default -> IMAGE_PATH + IMAGE_VERY_BAD;
        };
    }
}
