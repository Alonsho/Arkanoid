package gameplay;


import animation.AnimationRunner;
import animation.KeyPressStoppableAnimation;
import animation.WinScreen;
import animation.LoseScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;

import java.util.List;


/**
 * The type Game flow.
 *
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019 -03-03
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private GUI gui;
    private Counter score;
    private Counter lives;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar  the AnimationRunner
     * @param ks  the keyboard sensor
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.keyboardSensor = ks;
        this.animationRunner = ar;
        this.gui = gui;
        this.score = new Counter();
        this.lives = new Counter();
    }

    /**
     * runLevels - Run the given list of levels.
     *
     * @param levels the list of levels to run
     */
    public void runLevels(List<LevelInformation> levels) {
        this.lives.increase(7);
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner,
                    this.gui, this.score, this.lives);

            level.initialize();

            //play a turn as long as there are lives left
            while (this.lives.getValue() != 0) {
                // if there are no more block left, the level is over.
                if (level.getRemainingBlock().getValue() == 0) {
                    break;
                }
                level.playOneTurn();
            }
            // if there are no more lives left, no more levels should be played
            if (this.lives.getValue() == 0) {
                break;
            }
        }
        // at the end of the game, run the appropriate animation.
        if (this.lives.getValue() != 0) {
            animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new WinScreen(this.score)));
        } else {
            animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    "space", new LoseScreen(this.score)));
        }

    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Counter getScore() {
        return this.score;
    }
}
