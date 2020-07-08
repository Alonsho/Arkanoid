package animation;

import biuoop.DrawSurface;
import sprites.SpriteCollection;

import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class PauseScreen implements Animation {
    private SpriteCollection sprites;
    private String levelName;

    /**
     * PauseScreen - construct a PauseScreen object.
     * <p>
     *
     * <p>
     *
     * @param sprites - the sprites collection of the level to be printed in the background of the pause screen.
     * @param levelName - the name of the level to be printed at the top right corner.
     */
    public PauseScreen(SpriteCollection sprites, String levelName) {
        this.sprites = sprites;
        this.levelName = levelName;
    }

    /**
     * doOneFrame - is in charge of the logic, and drawing all sprites to the surface.
     * <p>
     *
     * <p>
     *
     *
     * @param d - the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(150, d.getHeight() / 2, "paused -- press space to continue", 32);
        d.setColor(Color.black);
        d.drawText(550, 15, "Level Name: " + this.levelName, 15);
    }

    /**
     * shouldStop - is in charge of ending the animation if needed.
     * <p>
     *
     * <p>
     *
     *
     * @return true if animation should end.
     */
    public boolean shouldStop() {
        return false;
    }
}
