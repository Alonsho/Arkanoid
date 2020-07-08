package animation;

import biuoop.DrawSurface;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public interface Animation {

    /**
     * doOneFrame - is in charge of the logic.
     * <p>
     *
     * <p>
     *
     *
     * @param d - the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * shouldStop - is in charge of ending the animation if needed.
     * <p>
     *
     * <p>
     *
     * @return true if animation should end.
     */
    boolean shouldStop();
}
