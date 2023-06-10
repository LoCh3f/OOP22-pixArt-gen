package it.unibo.pixart.view.abilitytest;

import it.unibo.pixart.model.pixel.Pixel;
import it.unibo.pixart.utilities.mirror.GenericMirror;
import it.unibo.pixart.utilities.mirror.Mirror;

import java.util.Collection;

/**
 * Tester logic, strategy pattern for GameView.
 */
public class TesterLogic implements Logic {


    private final Mirror<Pixel> gridMirror;

    /**
     * Default constructor.
     */
    public TesterLogic() {
        this.gridMirror = new GenericMirror<>();
    }

    /**
     * @param userGrid     draw made by the user, as a collection of pixels
     * @param solutionGrid draw to be made, as a collection of pixels
     * @return the path of the image to be shown
     */
    public String test(final Collection<Pixel> userGrid, final Collection<Pixel> solutionGrid) { //NOPMD this is not a test
        return templatePath(this.gridMirror.getDifference(solutionGrid, userGrid).size());
    }


    private String templatePath(final int difference) {
        if (difference <= TesterEnum.VERY_GOOD.getLimit()) {
            return TesterEnum.VERY_GOOD.getPath();
        } else if (difference < TesterEnum.GOOD.getLimit()) {
            return TesterEnum.GOOD.getPath();
        } else if (difference < TesterEnum.TRY_BETTER.getLimit()) {
            return TesterEnum.TRY_BETTER.getPath();
        } else if (difference < TesterEnum.BAD.getLimit()) {
            return TesterEnum.BAD.getPath();
        } else if (difference < TesterEnum.VERY_BAD.getLimit()) {
            return TesterEnum.VERY_BAD.getPath();
        } else if (difference < TesterEnum.TOO_MUCH.getLimit()) {
            return TesterEnum.TOO_MUCH.getPath();
        } else {
            return TesterEnum.WASTED.getPath();
        }


    }


}
