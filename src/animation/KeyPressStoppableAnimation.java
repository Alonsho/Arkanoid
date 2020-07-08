package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private String key;
    private KeyboardSensor ks;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the keyboard sensor
     * @param key       the key to end the animation
     * @param animation the animation to be run
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.ks = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        /*
        run the animation, stop if the stopping key is pressed, but not in the first frame of the animation.
         */
        this.animation.doOneFrame(d);
        if (this.ks.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    @Override
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            this.isAlreadyPressed = true;
            return true;
        }
        return false;
    }
}
