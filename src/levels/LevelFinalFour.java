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
public class LevelFinalFour implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity v = Velocity.fromAngleAndSpeed(-45, 5);
        list.add(v);
        v = Velocity.fromAngleAndSpeed(0, 5.5);
        list.add(v);
        v = Velocity.fromAngleAndSpeed(45, 5.5);
        list.add(v);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 7;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return ("Final Four");
    }

    @Override
    public Sprite getBackground() {
        return (new BackgroundFinalFour());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        double xLoc;
        double yLoc = 75;
        Point p;
        Block block;
        for (int i = 0; i < 7; i++) {
            yLoc += 25;
            xLoc = 724;
            if (i == 0) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.gray, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 1) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.red, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 2) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.yellow, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 3) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.green, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 4) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.white, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 5) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.pink, 1);
                    list.add(block);
                    xLoc -= 50;
                }
            }
            if (i == 6) {
                for (int j = 0; j < 15; j++) {
                    p = new Point(xLoc, yLoc);
                    Rectangle rec = new Rectangle(p, 50, 25);
                    block = new Block(rec, Color.cyan, 1);
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
        ball.setVelocity(Velocity.fromAngleAndSpeed(-45, 5.5));
        list.add(ball);
        ball = new Ball(400, 450, 6, java.awt.Color.white);
        ball.setVelocity(Velocity.fromAngleAndSpeed(0, 5.5));
        list.add(ball);
        ball = new Ball(500, 500, 6, java.awt.Color.white);
        ball.setVelocity(Velocity.fromAngleAndSpeed(45, 5.5));
        list.add(ball);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
