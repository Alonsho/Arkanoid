package animation;

import biuoop.DrawSurface;
import gameplay.Counter;

import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class LoseScreen implements Animation {
    private int starSize = 0;
    private int bigger = 2;
    private Counter score;

    /**
     * LoseScreen - construct a PauseScreen object.
     * <p>
     *
     * <p>
     *
     * @param score - the score Counter for the game.
     */
    public LoseScreen(Counter score) {
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
        d.setColor(Color.magenta.darker().darker());
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.black);
        d.drawText(270, 200, "You Lose!", 50);
        d.drawText(180, 250, "Your score is: " + this.score.getValue(), 50);
        d.drawText(310 - (this.starSize / 2), d.getHeight() / 2 + 150, "\u2639", 150 + this.starSize);
        this.starSize += bigger;
        if (this.starSize == 80) {
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
