package animation;


import biuoop.DrawSurface;
import sprites.SpriteCollection;
// The CountdownAnimation will display the given gameScreen,
// for numOfSeconds seconds, and on top of them it will show
// a countdown from countFrom back to 1, where each number will
// appear on the screen for (numOfSeconds / countFrom) seconds, before
// it is replaced with the next one.

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private double framesLeft;
    private int originalCountFrom;
    private boolean running;
    private String levelName;

    /**
     * CountdownAnimation - construct a CountdownAnimation object.
     * <p>
     *
     * <p>
     *
     * @param numOfSeconds - how long should the countdown last
     * @param countFrom - the number to start the countdown from.
     * @param gameScreen - the sprites collection to draw in the background.
     * @param levelName - the name of the level to be printed at the top right corner of the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, String levelName) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        // framesLeft is the amount of frames left to print the current number of the countdown.
        this.framesLeft = this.numOfSeconds / this.countFrom * 60;
        this.originalCountFrom = countFrom;
        this.running = true;
        this.levelName = levelName;
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
        //paint background
        d.setColor(java.awt.Color.black);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        //draw all sprites to the screen.
        this.gameScreen.drawAllOn(d);
        d.drawText(550, 15, "Level Name: " + this.levelName, 15);
        if (this.framesLeft <= 0) {
            // end the animation if countdown ended.
            if (this.countFrom == 0) {
                this.running = false;
                return;
            }
            // continue to next number in countdown.
            this.framesLeft = this.numOfSeconds / this.originalCountFrom * 60;
            this.countFrom--;
        }
        d.setColor(java.awt.Color.red);
        if (this.countFrom != 0) {
            d.drawText(392, d.getHeight() / 2, Integer.toString(countFrom), 32);
        } else {
            d.drawText(392, d.getHeight() / 2, "GO", 32);
        }
        this.framesLeft--;
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
        return !this.running;
    }
}
