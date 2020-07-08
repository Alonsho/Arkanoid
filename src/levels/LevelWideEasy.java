package levels;

import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class LevelWideEasy implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity v = new Velocity(0, 4);
        list.add(v);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return ("Wide Easy");
    }

    @Override
    public Sprite getBackground() {
        return (new BackgroundWideEasy());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Point p;
        Block block;
        Rectangle rec;
        for (int i = 0; i < 15; i++) {
            p = new Point(25 + (i * 50), 225);
            if (i == 0 || i == 1) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.red, 1);
                list.add(block);
                continue;
            }
            if (i == 2 || i == 3) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.orange, 1);
                list.add(block);
                continue;
            }
            if (i == 4 || i == 5) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.yellow, 1);
                list.add(block);
                continue;
            }
            if (i == 6 || i == 7 || i == 8) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.green, 1);
                list.add(block);
                continue;
            }
            if (i == 9 || i == 10) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.blue, 1);
                list.add(block);
                continue;
            }
            if (i == 11 || i == 12) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.pink, 1);
                list.add(block);
                continue;
            }
            if (i == 13 || i == 14) {
                rec = new Rectangle(p, 50, 25);
                block = new Block(rec, Color.cyan, 1);
                list.add(block);
                continue;
            }
        }
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        double yLoc = 375;
        double angle = -50;
        double j = 25;
        for (int i = 0; i < 11; i++) {
            Point p = new Point(150 + (i * 50), yLoc);
            Ball ball = new Ball(p, 6, Color.white);
            ball.setVelocity(Velocity.fromAngleAndSpeed(angle, 5));
            list.add(ball);
            angle += 10;
            if (i < 4) {
                yLoc -= j;
                j -= 5;
            }
            if (i > 4) {
                yLoc += j;
                j += 5;
            }
            if (i == 4) {
                i++;
                j += 5;
            }
        }
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
