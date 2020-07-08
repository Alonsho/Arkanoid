package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class BackgroundGreen3 implements Sprite {

    @Override
    public void drawOn(DrawSurface d) {
        // creates the background for level
        d.setColor(Color.GREEN.darker().darker());
        d.fillRectangle(25, 45, d.getWidth() - 49, d.getHeight() - 20);
        d.setColor(Color.black);
        d.fillRectangle(75, 400, 110, 200);
        d.setColor(Color.white);
        d.fillRectangle(85, 410, 90, 200);
        d.setColor(Color.black);
        for (int i = 445; i < 600; i += 42) {
            d.fillRectangle(85, i, 90, 7);
        }
        for (int i = 96; i < 165; i += 20) {
            d.fillRectangle(i, 410, 7, 190);
        }
        d.setColor(Color.gray.darker().darker());
        d.fillRectangle(116, 340, 34, 60);
        d.setColor(Color.gray.darker());
        d.fillRectangle(127, 125, 12, 215);
        d.setColor(Color.decode("#BDB76B"));
        d.fillCircle(133, 125, 14);
        d.setColor(Color.decode("#DC143C"));
        d.fillCircle(133, 125, 9);
        d.setColor(Color.white);
        d.fillCircle(133, 125, 3);
    }

    @Override
    public void timePassed() {
        return;
    }
}
