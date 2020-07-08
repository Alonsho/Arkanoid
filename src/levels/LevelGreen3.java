package levels;

import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class LevelGreen3 implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity v = new Velocity(-4, -4);
        list.add(v);
        v = new Velocity(4, -4);
        list.add(v);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return ("Green 3");
    }

    @Override
    public Sprite getBackground() {
        return (new BackgroundGreen3());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        double xLoc;
        double yLoc = 125;
        Point p;
        Block block;
        for (int i = 0; i < 5; i++) {
            yLoc += 25;
            xLoc = 724;
            if (i == 0) {
                for (int j = 0; j < 10; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.gray, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 1) {
                for (int j = 0; j < 9; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.red, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 2) {
                for (int j = 0; j < 8; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.yellow, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 3) {
                for (int j = 0; j < 7; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.blue, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 4) {
                for (int j = 0; j < 6; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.white, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
        }
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        Ball ball = new Ball(300, 500, 6, java.awt.Color.white);
        ball.setVelocity(-4, -4);
        list.add(ball);
        ball = new Ball(500, 500, 6, java.awt.Color.white);
        ball.setVelocity(4, -4);
        list.add(ball);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
