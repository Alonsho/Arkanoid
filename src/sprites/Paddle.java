package sprites;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gameplay.GameLevel;
import gameplay.Velocity;
import geometry.Point;
import geometry.Rectangle;

import java.awt.Color;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class Paddle implements Sprite, Collidable {
    //fields of Paddle.
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rec;
    private Paddle collisionPad;
    private double speed;
    /**
     * Paddle - construct a Paddle object.
     * <p>
     *
     * <p>
     *
     * @param rec - the rectangle shape to create the paddle from.
     * @param keyboard - the KeyboardSensor to assign to the paddle.
     * @param speed - the speed to be given to the paddle.
     */
    public Paddle(Rectangle rec, KeyboardSensor keyboard, double speed) {
        this.rec = rec;
        this.keyboard = keyboard;
        this.speed = speed;
    }
    /**
     * moveLeft - move the paddle one step to the left.
     * <p>
     *
     * <p>
     *
     * NOTE: for now, the paddle speed is set to 4.
     */
    public void moveLeft() {
      /*
      if the movement of the paddle will cause it to go outside the left border, move the paddle to be
      adjacent to the border without crossing it.
      */
        if (this.rec.getUpperLeft().getX() - this.speed <= 25) {
            Point newCorner = new Point(25, this.rec.getUpperLeft().getY());
            Rectangle newRec = new Rectangle(newCorner, this.rec.getWidth(), this.rec.getHeight());
            this.rec = newRec;
            return;
        }
      /*
      otherwise, move the paddle regularly.
      */
        Point newCorner = new Point(this.rec.getUpperLeft().getX() - this.speed, this.rec.getUpperLeft().getY());
        Rectangle newRec = new Rectangle(newCorner, this.rec.getWidth(), this.rec.getHeight());
        this.rec = newRec;
    }
    /**
     * moveRight - move the paddle one step to the right.
     * <p>
     *
     * <p>
     *
     * NOTE: for now, the paddle speed is set to 4.
     */
    public void moveRight() {
      /*
      if the movement of the paddle will cause it to go outside the right border, move the paddle to be
      adjacent to the border without crossing it.
      */
        if (this.rec.getLowerRight().getX() + this.speed >= 775) {
            Point newCorner = new Point(774 - this.rec.getWidth(), this.rec.getUpperLeft().getY());
            Rectangle newRec = new Rectangle(newCorner, this.rec.getWidth(), this.rec.getHeight());
            this.rec = newRec;
            return;
        }
      /*
      otherwise, move the paddle regularly.
      */
        Point newCorner = new Point(this.rec.getUpperLeft().getX() + this.speed, this.rec.getUpperLeft().getY());
        Rectangle newRec = new Rectangle(newCorner, this.rec.getWidth(), this.rec.getHeight());
        this.rec = newRec;
    }
    /**
     * timePassed - notify the paddle to make an action.
     * <p>
     * the action the ball does is move on the screen, if one of the arrow keys is pressed.
     * <p>
     *
     *
     *
     */
    public void timePassed() {
        //check if any of the arrow keys was pressed, and move the paddle accordingly.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }
    /**
     * drawOn - draw the paddle on the given DrawSurface.
     * <p>
     *
     * <p>
     *
     *
     * @param d - the surface to be drawn on
     */
    public void drawOn(DrawSurface d) {
        //for now, paddle color is set to white.
        d.setColor(Color.orange);
      /*
       draw the paddle on the surface, and draw its borders in black.
       casting is done to support the draw and fill methods.
       */
        d.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        d.setColor(java.awt.Color.black);
        d.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    /**
     * getCollisionRectangle - Return the "collision shape" of the paddle.
     * <p>
     *
     * <p>
     *
     *
     * @return the "collision shape" of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }
    /**
     * hit - Notify the paddle that we collided with it at collisionPoint with a given velocity.
     * <p>
     *
     * <p>
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us,
     * and the location on the paddle where the collision occurred).
     *
     *
     * @param collisionPoint - the point where the collision occurs
     * @param currentVelocity - the velocity of the ball before the collision.
     * @param hitter - the ball that hits the block
     * @return the new velocity expected after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity v;
      /*
      if the ball hits the paddle from underneath, just change the vertical speed of the ball.
      */
        if (currentVelocity.getDy() < 0) {
            v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
            return v;
        }
        // calculate the actual size of the velocity vector of the ball
        double actualSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2));
      /*
      change the velocity of the ball according to the location on paddle in which the collision occurs.
      */
        if (collisionPoint.getX() <= this.rec.getWidth() / 5 + this.rec.getUpperLeft().getX()) {
            v = Velocity.fromAngleAndSpeed(300, actualSpeed);
        } else if (collisionPoint.getX() <= this.rec.getWidth() / 5 * 2 + this.rec.getUpperLeft().getX()) {
            v = Velocity.fromAngleAndSpeed(330, actualSpeed);
        } else if (collisionPoint.getX() <= this.rec.getWidth() / 5 * 3 + this.rec.getUpperLeft().getX()) {
            v = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        } else if (collisionPoint.getX() <= this.rec.getWidth() / 5 * 4 + this.rec.getUpperLeft().getX()) {
            v = Velocity.fromAngleAndSpeed(30, actualSpeed);
        } else {
            v = Velocity.fromAngleAndSpeed(60, actualSpeed);
        }
        return v;
    }

    /**
     * addToGame - add the paddle to the gameLevel.
     * <p>
     *
     * <p>
     *
     *
     * @param gameLevel - the gameLevel to add the block to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.getSprites().addSprite(this);
      /*
      in order to avoid the ball from getting stuck inside the paddle, we will not consider the whole paddle
      to be a collidable. we create a new paddle from only the top edge of the old paddle, and consider only the
      new paddle to be collidable. that way the ball might be able to go inside the paddle but it will not
      get stuck inside.
      */
        Rectangle topRec = new Rectangle(this.rec.getUpperLeft(), this.rec.getWidth(), 0);
        Paddle top = new Paddle(topRec, this.keyboard, this.speed);
        this.collisionPad = top;
        gameLevel.getSprites().addSprite(top);
        gameLevel.getEnvironment().addCollidable(top);
    }

    /**
     * removeFromGame - remove the paddle from the gameLevel.
     * <p>
     *
     * <p>
     *
     *
     * @param gameLevel - the gameLevel to remove the paddle from.
     */
    public void removeFromGame(GameLevel gameLevel) {
        //removes the paddle and its collision pad from the relevant lists
        gameLevel.removeSprite(this);
        gameLevel.removeSprite(this.collisionPad);
        gameLevel.removeCollidable(this.collisionPad);
    }
}