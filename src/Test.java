import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameplay.GameFlow;
import levelreading.LevelSpecificationReader;
import levels.LevelInformation;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * The type Test.
 */
public class Test {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gf = new GameFlow(runner, ks, gui);
        LevelSpecificationReader spr = new LevelSpecificationReader();
        File file = new File("easy_level_definitions.txt");
        try {
            BufferedReader is = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            List<LevelInformation> list = spr.fromReader(is);
            gf.runLevels(list);
            return;
        } catch (IOException e) {
            System.out.println("Something went wrong while writing!");
        }

    }
}
