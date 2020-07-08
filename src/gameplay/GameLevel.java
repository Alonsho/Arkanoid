package gameplay;

import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometry.Point;
import geometry.Rectangle;
import levels.LevelInformation;
import sprites.Block;
import sprites.Collidable;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.ScoreIndicator;
import sprites.LivesIndicator;
import sprites.Ball;
import sprites.Paddle;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class GameLevel implements Animation {
    //fields of GameLevel.
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlock;
    private Counter remainingBalls;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private Paddle paddle;
    private KeyboardSensor k;
    private LevelInformation li;

    /**
     * GameLevel - construct a GameLevel object.
     * <p>
     *
     * <p>
     *
     * @param li - the level that is to be run.
     * @param ks - the keyboard sensor.
     * @param ar - the animation runner.
     * @param gui - the gui.
     * @param score - the score counter of the current game.
     * @param lives - the lives counter for the current game
     */
    public GameLevel(LevelInformation li, KeyboardSensor ks, AnimationRunner ar,
                     GUI gui, Counter score, Counter lives) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlock = new Counter();
        this.remainingBalls = new Counter();
        this.li = li;
        this.environment.setGui(gui);
        this.k = ks;
        this.runner = ar;
        this.score = score;
        this.lives = lives;
    }
    /**
     * addCollidable - add the given collidable to the game's game environment.
     * <p>
     *
     * <p>
     *
     * @param c - the new collidable to be added to the list.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
        return;
    }

    /**
     * removeCollidable - remove the given collidable from the game's game environment.
     * <p>
     *
     * <p>
     *
     * @param c - the collidable to be removed from the list.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
        return;
    }
    /**
     * addSprite - add a new sprite to the game's sprite collection.
     * <p>
     *
     * <p>
     *
     * @param s - the new sprite to be added.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
        return;
    }

    /**
     * removeSprite - remove the given sprite from the game's sprite collection.
     * <p>
     *
     * <p>
     *
     * @param s - the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
        return;
    }
    /**
     * initialize - Initialize a new game.
     * <p>
     *
     * <p>
     *
     *
     */
    public void initialize() {
        int guiWidth = 800;
        int guiHeight = 600;
        this.sprites.addSprite(this.li.getBackground());
        // create a BallRemover, BlockRemover, and a ScoreTrackingListener for this game
        BlockRemover blockRemover = new BlockRemover(this, this.remainingBlock);
        BallRemover ballRemover = new BallRemover(this, remainingBalls);
        ScoreTrackingListener scoreTracker = new ScoreTrackingListener(this.score);
        //create the death region and add it to the game
        Point p1 = new Point(0, 608);
        Rectangle rec = new Rectangle(p1, guiWidth, 20);
        Block deathRegion = new Block(rec, java.awt.Color.gray, 0);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(ballRemover);
        // create the 3 blocks that block the borders and add them to the game.
        p1 = new Point(0, 20);
        rec = new Rectangle(p1, guiWidth, 25);
        Block block = new Block(rec, java.awt.Color.gray, 0);
        block.addToGame(this);
        p1 = new Point(775, 0);
        rec = new Rectangle(p1, 25, guiHeight);
        block = new Block(rec, java.awt.Color.gray, 0);
        block.addToGame(this);
        p1 = new Point(0, 0);
        rec = new Rectangle(p1, 25, guiHeight);
        block = new Block(rec, java.awt.Color.gray, 0);
        block.addToGame(this);
        // create a ScoreIndicator at the top of the screen
        p1 = new Point(0, 0);
        rec = new Rectangle(p1, guiWidth, 21);
        ScoreIndicator indicator = new ScoreIndicator(rec, this.score);
        indicator.addToGame(this);
        //create a LivesIndicator at the top of the screen
        p1 = new Point(0, 1);
        rec = new Rectangle(p1, 40, 19);
        LivesIndicator livesCount = new LivesIndicator(rec, this.lives);
        livesCount.addToGame(this);

      /*
      the following block creates a nice pattern of blocks (similar to the pattern given in the
      exercise page), sized 50x25 and adds them to the game, column by column. 12 columns in total.
      */
      for (Block bl : this.li.blocks()) {
          bl.addToGame(this);
          bl.addHitListener(blockRemover);
          bl.addHitListener(scoreTracker);
      }
        this.remainingBlock.increase(this.li.numberOfBlocksToRemove());
    }
    /**
     * playOneTurn - runs the animation loop for 1 turn.
     * <p>
     *
     * <p>
     *
     *
     *
     */
    public void playOneTurn() {

        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites, this.li.levelName()));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);

    }

    /**
     * getEnvironment - Return the GameEnvironment of the game.
     * <p>
     *
     * <p>
     *
     *
     * @return the GameEnvironment of the game.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
    /**
     * getSprites - Return the SpriteCollection of the game.
     * <p>
     *
     * <p>
     *
     *
     * @return the SpriteCollection of the game.
     */
    public SpriteCollection getSprites() {
        return this.sprites;
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
        return (!this.running);
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
        //draw all sprites to the screen.
        this.sprites.drawAllOn(d);
        d.drawText(550, 15, "Level Name: " + this.li.levelName(), 15);
        //notify all sprites that time has passed.
        this.sprites.notifyAllTimePassed();
        // check if all blocks were destroyed
        if (this.remainingBlock.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        // check if all balls are lost
        if (this.remainingBalls.getValue() == 0) {
            // remove the paddle from the game (a new one will be created)
            this.paddle.removeFromGame(this);
            this.lives.decrease(1);
            this.running = false;
        }
        // pause game if needed.
        if (this.k.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.k, "space",
                    new PauseScreen(this.sprites, this.li.levelName())));
        }
    }

    /**
     * createBallsOnTopOfPaddle - creates a new paddle and new balls.
     * <p>
     *
     * <p>
     *
     *
     */
    public void createBallsOnTopOfPaddle() {
        //create a KeyboardSensor for the paddle
        for (Ball ball : this.li.balls()) {
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
        //create a paddle and add it to the game.
        Point p1 = new Point(400 - (this.li.paddleWidth() / 2), 580);
        Rectangle rec = new Rectangle(p1, this.li.paddleWidth(), 20);
        this.paddle = new Paddle(rec, this.k, this.li.paddleSpeed());
        this.paddle.addToGame(this);
    }

    /**
     * getRemainingBlock - returns the counter of the remaining blocks.
     * <p>
     *
     * <p>
     *
     *
     * @return the counter of the remaining blocks.
     */
    public Counter getRemainingBlock() {
        return this.remainingBlock;
    }
}