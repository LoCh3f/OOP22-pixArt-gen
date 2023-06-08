package it.unibo.pixArt.view.abilitytest;

import it.unibo.pixArt.model.pixel.Pixel;
import it.unibo.pixArt.utilities.mirror.GenericMirror;
import it.unibo.pixArt.utilities.mirror.Mirror;

import java.util.Collection;

public class TesterLogic implements Logic {


    private final Mirror<Pixel> gridMirror;

    public TesterLogic() {
        this.gridMirror = new GenericMirror<>();
    }

    public String test(final Collection<Pixel> userGrid, final Collection<Pixel> solutionGrid) {
        return templatePath(this.gridMirror.getDifference(solutionGrid, userGrid).size());
    }


    private String templatePath(final int difference) {
        if (difference == TesterEnum.VERY_GOOD.getLimit()) {
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
