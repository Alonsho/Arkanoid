package levelreading;

import geometry.Point;
import geometry.Rectangle;
import sprites.Block;

import java.awt.Color;
import java.awt.Image;
import java.util.HashMap;

/**
 * The type Basic block creator.
 */
public class BasicBlockCreator implements BlockCreator {

    private int height;
    private int width;
    private int hitPoints;
    private HashMap<Integer, Color> hitToColorMap;
    private HashMap<Integer, Image> hitToImgMap;
    private Color stroke = null;
    private Color defaultFillColor;
    private Image defaultFillImg;


    /**
     * Instantiates a new Basic block creator.
     *
     * @param attributes       the attributes
     * @param hitToColorMap    the hit to color map
     * @param hitToImgMap      the hit to img map
     * @param stroke           the stroke
     * @param defaultFillColor the default fill color
     * @param defaultFillImg   the default fill img
     */
    public BasicBlockCreator(int[] attributes, HashMap<Integer, Color> hitToColorMap,
                             HashMap<Integer, Image> hitToImgMap, Color stroke,
                             Color defaultFillColor, Image defaultFillImg) {
        this.height = attributes[0];
        this.width = attributes[1];
        this.hitPoints = attributes[2];
        this.hitToColorMap = hitToColorMap;
        this.hitToImgMap = hitToImgMap;
        this.stroke = stroke;
        this.defaultFillColor = defaultFillColor;
        this.defaultFillImg = defaultFillImg;
    }

    /**
     * Instantiates a new Basic block creator.
     *
     * @param xpos       the x position of the block
     * @param ypos       the y position of the block
     * @return block     the created block
     */
    public Block create(int xpos, int ypos) {
        Point p = new Point(xpos, ypos);
        Rectangle rec = new Rectangle(p, this.width, this.height);
        Block block = new Block(rec, this.hitPoints, this.hitToColorMap, this.hitToImgMap, this.stroke,
                this.defaultFillColor, this.defaultFillImg);
        return block;
    }
}
