package navigation;

import animation.AnimationRunner;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 */
public class MenuAnimation<T> implements Menu<T> {
    private AnimationRunner runner;
    private LinkedHashMap<String, T> keysToTasks;
    private LinkedHashMap<String, String> keysToMessages;
    private Map<String, Menu<T>> subMenuMap;
    private KeyboardSensor ks;
    private boolean stop;
    private boolean isAlreadyPressed;
    private String pressedKey;
    private boolean somethingIsPressed = false;
    private Image backgroundImage;


    /**
     * Instantiates a new Menu animation.
     *
     * @param ks     the ks
     * @param runner the runner
     */
    public MenuAnimation(KeyboardSensor ks, AnimationRunner runner) {
        this.subMenuMap = new HashMap<>();
        this.runner = runner;
        this.ks = ks;
        this.stop = false;
        this.isAlreadyPressed = true;
        this.keysToMessages = new LinkedHashMap<>();
        this.keysToTasks = new LinkedHashMap<>();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("background_images/arcade.jpg");
        try {
            this.backgroundImage = ImageIO.read(is);
        } catch (IOException e) {
            System.out.println("Something went wrong while reading background image!");
        }
    }

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param returnVal the value to return if the key is pressed
     */
    public void addSelection(String key, String message, T returnVal) {
        this.keysToTasks.put(key, returnVal);
        this.keysToMessages.put(key, message);
    }

    /**
     * Add sub menu.
     *
     * @param key     the key
     * @param message the message
     * @param subMenu the sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenu) {
        this.subMenuMap.put(key, subMenu);
        this.keysToMessages.put(key, message);
    }

    /**
     * getStatus.
     *
     * @return the appripriate status for the key
     */
    public T getStatus() {
        if (this.subMenuMap.containsKey(this.pressedKey)) {
            return this.subMenuMap.get(this.pressedKey).getStatus();
        } else {
            return this.keysToTasks.get(this.pressedKey);
        }
    }

    /**
     * doOneFrame - is in charge of the logic.
     * <p>
     *
     * <p>
     *
     *
     * @param d - the draw surface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.magenta.darker().darker());
        d.drawImage(0, 0, this.backgroundImage);
        d.setColor(Color.magenta);
        d.drawText(280, 370, "ARKANOID", 40);
        int i = 420;
        for (String key : this.keysToMessages.keySet()) {
            d.drawText(210, i, "Press \"" + key + "\" to " + this.keysToMessages.get(key), 30);
            i += 50;
        }
        this.somethingIsPressed = false;
        for (String key : this.keysToMessages.keySet()) {
            if (this.ks.isPressed(key)) {
                this.somethingIsPressed = true;
                if (!this.isAlreadyPressed) {
                    this.stop = true;
                    this.pressedKey = key;
                    if (this.subMenuMap.containsKey(key)) {
                        Menu<T> subMenu = this.subMenuMap.get(key);
                        runner.run(subMenu);
                    }
                    break;
                }
            }
        }
        if (!this.somethingIsPressed) {
            this.isAlreadyPressed = false;
            this.somethingIsPressed = true;
        }
    }

    /**
     * shouldStop - is in charge of ending the animation if needed.
     * <p>
     *
     * <p>
     *
     * @return true if animation should end.
     */
    public boolean shouldStop() {
        if (this.stop) {
            this.stop = false;
            this.isAlreadyPressed = true;
            this.somethingIsPressed = false;
            return true;
        }
        return false;
    }
}
