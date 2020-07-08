package gameplay;

import sprites.Ball;
import sprites.Block;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03
 */



/**
 * a BlockRemover is in charge of removing blocks from the gameLevel, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    //fields of BlockRemover
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Ball remover.
     *
     * @param gameLevel the gameLevel that is being played
     * @param totalBlocks the amount of blocks at the beginning of the gameLevel
     */
    public BlockRemover(GameLevel gameLevel, Counter totalBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = totalBlocks;
    }
    /**
     * hitEvent - removes a block if it just lost its final hit Point.
     *
     * @param beingHit the block that is hit by the ball
     * @param hitter the ball that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // check if the block has only 1 hit point left (therefore will be removed)
        if (beingHit.getHitPoints() == 1) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }
    }
}