package animation;

import biuoop.DrawSurface;
import gameplay.Counter;

import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class WinScreen implements Animation  {
    private int starSize = 0;
    private int bigger = 2;
    private Counter score;

    /**
     * PauseScreen - construct a PauseScreen object.
     * <p>
     *
     * <p>
     *
     * @param score - the score Counter to be printed at the top of the screen.
     */
    public WinScreen(Counter score) {
        this.score = score;
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
        d.setColor(Color.cyan);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.yellow);
        d.drawText(270, 200, "You Win!", 50);
        d.drawText(180, 250, "Your score is: " + this.score.getValue(), 50);
        d.drawText(310 - (this.starSize / 2), d.getHeight() / 2 + 150, "\u2605", 150 + this.starSize);
        this.starSize += bigger;
        if (this.starSize == 100) {
            this.bigger = -2;
        }
        if (this.starSize == 0) {
            this.bigger = 2;
        }
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
