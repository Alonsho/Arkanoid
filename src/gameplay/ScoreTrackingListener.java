package gameplay;


import sprites.Ball;
import sprites.Block;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class ScoreTrackingListener implements HitListener {
    //fields of ScoreTrackingListener
    private Counter currentScore;

    /**
     * ScoreTrackingListener - creates a new ScoreTrackingListener object.
     *
     * @param scoreCounter -  the counter given to the ScoreTrackingListener
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * hitEvent - adds score according to the given requirements in the exercise page.
     *
     * @param beingHit the block that is hit by the ball
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // add 5 points for every hit
        this.currentScore.increase(5);
        // if a block is destroyed, add additional 10 points
        if (beingHit.getHitPoints() == 1) {
            this.currentScore.increase(10);
        }
    }
}