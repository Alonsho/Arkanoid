package levels;


import gameplay.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.util.List;


/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public interface LevelInformation {

    /**
     * numberOfBalls - returns the amount of balls that are played in the level.
     * <p>
     *
     * <p>
     *
     * @return the amount of balls that are played in the level.
     */
    int numberOfBalls();

    /**
     * initialBallVelocities - rcreates a list with the initial velocity of the balls and returns it.
     * <p>
     *
     * <p>
     *
     * @return the list of velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * paddleSpeed - returns the paddle speed in the level.
     * <p>
     *
     * <p>
     *
     * @return the paddle speed in the level.
     */
    int paddleSpeed();

    /**
     * paddleWidth - returns the paddle width in the level.
     * <p>
     *
     * <p>
     *
     * @return the paddle width in the level.
     */
    int paddleWidth();

    /**
     * levelName - returns the name of the level.
     * <p>
     *
     * <p>
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * getBackground - returns the background of the level.
     * <p>
     *
     * <p>
     *
     * @return the background of the level.
     */
    Sprite getBackground();

    /**
     * blocks - creates a list of all the blocks unique to the level and returns it.
     * <p>
     * border blocks and death region block are not included in this list
     * <p>
     *
     * @return the list of blocks
     */
    List<Block> blocks();

    /**
     * balls - creates a list of all the balls that are to be played in the level and returns it.
     * <p>
     *
     * <p>
     *
     * @return the list of balls.
     */
    List<Ball> balls();

    /**
     * numberOfBlocksToRemove - returns the amount of blocks that are to be removed in the level.
     * <p>
     *
     * <p>
     *
     * @return returns the amount of blocks that are to be removed in the level.
     */
    int numberOfBlocksToRemove();
}
