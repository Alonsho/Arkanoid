package animation;

import biuoop.DrawSurface;
import gameplay.HighScoresTable;
import gameplay.ScoreInfo;


/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable scores;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.scores = scores;
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
        int i = 50;
        for (ScoreInfo si1 : scores.getHighScores()) {
            d.drawText(20, i, si1.getName() + ": " + si1.getScore(), 30);
            i += 50;
        }
    }

    /**
     * shouldStop - is in charge of ending the animation if needed.
     * <p>
     *
     * <p>
     *
     * @return true if animation should end.
     */
    public boolean shouldStop() {
        return false;
    }
}