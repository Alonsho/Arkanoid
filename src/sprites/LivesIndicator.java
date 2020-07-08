package sprites;

import biuoop.DrawSurface;
import gameplay.Counter;
import gameplay.GameLevel;
import geometry.Rectangle;


/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class LivesIndicator implements Sprite {
    // fields of LivesIndicator
    private Rectangle rec;
    private Counter lives;

    /**
     * LivesIndicator - creates a new LivesIndicator object.
     *
     * @param rec -  the rectangle which the counter should be printer on
     * @param lives -  the amount of lives left
     */
    public LivesIndicator(Rectangle rec, Counter lives) {
        this.rec = rec;
        this.lives = lives;
    }

    /**
     * drawOn - draw the LivesIndicator on the given DrawSurface.
     * <p>
     *
     * <p>
     *
     *
     * @param surface - the surface to be drawn on
     */
    public void drawOn(DrawSurface surface) {
       /*
       draw the LivesIndicator on the surface. it will be drawn over the ScoreIndicator
       casting is done to support the draw and fill methods.
       */
        surface.setColor(java.awt.Color.gray.brighter());
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        //write the counter on the middle of the block.
        java.lang.String str;
        surface.setColor(java.awt.Color.red);
        str = Integer.toString(this.lives.getValue());
        surface.drawText((int) (this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 2) - 15),
                (int) (this.rec.getUpperLeft().getY() + (this.rec.getHeight() / 2) + 7), "\u2665: " + str, 17);
    }

    /**
     * timePassed - notify the LivesIndicator that time has passed.
     * <p>
     * the LivesIndicator doesn't do any action, because it doesn't move.
     * <p>
     *
     *
     *
     */
    public void timePassed() {
        return;
    }

    /**
     * addToGame - add the LivesIndicator to the gameLevel.
     * <p>
     *
     * <p>
     *
     *
     * @param gameLevel - the gameLevel to add the LivesIndicator to.
     **/
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        return;
    }
}
