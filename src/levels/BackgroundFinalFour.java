package levels;

import biuoop.DrawSurface;
import sprites.Sprite;

import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class BackgroundFinalFour implements Sprite {
    private int rainFrame = 0;

    @Override
    public void drawOn(DrawSurface d) {
        // creates the background for level
        d.setColor(Color.decode("#1C075F"));
        d.fillRectangle(25, 45, d.getWidth() - 49, d.getHeight() - 20);
        d.setColor(Color.white);
        for (int i = 116; i <= 240; i += 10) {
            for (int j = 340; j < 600; j += 20) {
                d.drawLine(i, j + this.rainFrame, i, j + this.rainFrame + 3);
            }
        }
        for (int i = 555; i <= 660; i += 10) {
            for (int j = 440; j < 600; j += 20) {
                d.drawLine(i, j + this.rainFrame, i, j + this.rainFrame + 3);
            }
        }
        d.setColor(Color.decode("#D3D3D3"));
        d.fillCircle(116, 340, 25);
        d.fillCircle(136, 360, 30);
        d.setColor(Color.decode("#C0C0C0"));
        d.fillCircle(166, 335, 35);
        d.setColor(Color.decode("#A9A9A9"));
        d.fillCircle(220, 350, 40);
        d.fillCircle(180, 370, 25);
        d.setColor(Color.decode("#D3D3D3"));
        d.fillCircle(555, 440, 25);
        d.fillCircle(576, 475, 30);
        d.setColor(Color.decode("#C0C0C0"));
        d.fillCircle(596, 445, 35);
        d.setColor(Color.decode("#A9A9A9"));
        d.fillCircle(650, 460, 40);
        d.fillCircle(615, 475, 25);
    }

    @Override
    public void timePassed() {
        this.rainFrame++;
        if (this.rainFrame == 17) {
            this.rainFrame = 0;
        }
        return;
    }
}
