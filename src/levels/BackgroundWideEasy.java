package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class BackgroundWideEasy implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // creates the background for level
        d.setColor(java.awt.Color.white);
        d.fillRectangle(25, 45, d.getWidth() - 49, d.getHeight() - 20);
        d.setColor(new java.awt.Color(237, 255, 143));
        for (int i = 0; i < 700; i += 10) {
            d.drawLine(135, 125, i, 225);
        }
        d.fillCircle(135, 125, 70);
        d.setColor(new java.awt.Color(255, 249, 66));
        d.fillCircle(135, 125, 60);
        d.setColor(new java.awt.Color(255, 202, 14));
        d.fillCircle(135, 125, 50);
    }

    @Override
    public void timePassed() {
        return;
    }
}
