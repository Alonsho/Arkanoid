package levels;

import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class LevelDirectHit implements LevelInformation {

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        Velocity v = new Velocity(0, -2);
        list.add(v);
        return list;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return ("Direct Hit");
    }

    @Override
    public Sprite getBackground() {
        return (new BackgroundDirectHit());
    }

    @Override
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        Point p = new Point(380, 180);
        Rectangle rec = new Rectangle(p, 40, 40);
        Block block = new Block(rec, java.awt.Color.red, 1);
        list.add(block);
        return list;
    }

    @Override
    public List<Ball> balls() {
        List<Ball> list = new ArrayList<>();
        Ball ball = new Ball(400, 380, 6, java.awt.Color.white);
        ball.setVelocity(0, -2);
        list.add(ball);
        return list;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}

