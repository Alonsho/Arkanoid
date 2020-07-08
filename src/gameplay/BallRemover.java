package gameplay;

import sprites.Ball;
import sprites.Block;


/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019 -03-03
 */

/**
 * a BallRemover is in charge of removing balls from the gameLevel, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    //fields of BallRemover
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel the gameLevel that is being played
     * @param totalBalls the amount of balls at the beginning of the gameLevel
     */
    public BallRemover(GameLevel gameLevel, Counter totalBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = totalBalls;
    }

    /**
     * removes a ball from the gameLevel whenever it hits the death region and decreases the balls count by 1.
     *
     * @param beingHit the block that is hit by the ball (the death region)
     * @param hitter the ball that is to be taken out of the gameLevel
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.gameLevel);
        this.remainingBalls.decrease(1);
    }
}
