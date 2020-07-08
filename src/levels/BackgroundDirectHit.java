package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class BackgroundDirectHit implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // creates the background for the level
        d.setColor(java.awt.Color.black);
        d.fillRectangle(25, 45, d.getWidth() - 49, d.getHeight() - 19);
        d.setColor(java.awt.Color.blue);
        d.drawCircle(400, 200, 120);
        d.drawCircle(400, 200, 90);
        d.drawCircle(400, 200, 60);
        d.drawLine(260, 200, 370, 200);
        d.drawLine(400, 60, 400, 170);
        d.drawLine(540, 200, 430, 200);
        d.drawLine(400, 340, 400, 230);
    }

    @Override
    public void timePassed() {
        return;
    }
}
