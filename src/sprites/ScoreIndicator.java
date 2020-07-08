package sprites;

import biuoop.DrawSurface;
import gameplay.Counter;
import gameplay.GameLevel;
import geometry.Rectangle;


/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class ScoreIndicator implements Sprite {
    private Rectangle rec;
    private Counter score;

    /**
     * ScoreIndicator - creates a new ScoreIndicator object.
     *
     * @param rec -  the rectangle which the counter should be printer on
     * @param score -  the amount of lives left
     */
    public ScoreIndicator(Rectangle rec, Counter score) {
        this.rec = rec;
        this.score = score;
    }

    /**
     * drawOn - draw the ScoreIndicator on the given DrawSurface.
     * <p>
     *
     * <p>
     *
     *
     * @param surface - the surface to be drawn on
     */
    public void drawOn(DrawSurface surface) {
       /*
       draw the block on the surface, and draw its borders in black.
       casting is done to support the draw and fill methods.
       */
        surface.setColor(java.awt.Color.gray.brighter());
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        //write the counter on the middle of the block.
        surface.setColor(java.awt.Color.black);
        java.lang.String str;
        str = Integer.toString(this.score.getValue());
        surface.drawText((int) (this.rec.getUpperLeft().getX() + (this.rec.getWidth() / 2) - 40),
                (int) (this.rec.getUpperLeft().getY() + (this.rec.getHeight() / 2) + 5), "Score: " + str, 17);
    }

    /**
     * timePassed - notify the ScoreIndicator that time has passed.
     * <p>
     * the ScoreIndicator doesn't do any action, because it doesn't move.
     * <p>
     *
     *
     *
     */
    public void timePassed() {
        return;
    }

    /**
     * addToGame - add the ScoreIndicator to the gameLevel.
     * <p>
     *
     * <p>
     *
     *
     * @param gameLevel - the gameLevel to add the ScoreIndicator to.
     **/
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
        return;
    }
}
