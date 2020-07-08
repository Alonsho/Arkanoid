
import animation.Animation;
import animation.AnimationRunner;
import animation.HighScoresAnimation;
import animation.KeyPressStoppableAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gameplay.GameFlow;
import gameplay.HighScoresTable;
import gameplay.ScoreInfo;
import levelreading.LevelSpecificationReader;
import levels.LevelInformation;
import navigation.Menu;
import navigation.MenuAnimation;
import navigation.Task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Alon Shoval <alon_shoval@hotmail.com>
 * @version 1.0
 * @since 2019-03-03  */
public class Ass7Game {
    /**
     * main - creates a new game, initializes it, and runs it.
     * <p>
     *
     * <p>
     *
     *
     *@param args - user inputted arguments. not used in this class.
     */
    public static void main(String[] args) {
        HighScoresTable hst = new HighScoresTable(10);
        File file = new File("highscores.txt");
        if (file.exists()) {
            try {
                hst.load(file);
            } catch (IOException e) {
                System.out.println("Something went wrong while reading!");
            }
        } else {
            try {
                hst.save(file);
            } catch (IOException e) {
                System.out.println("Something went wrong while writing!");
            }
        }

        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        Menu<Task<Void>> lvlSetMenu = new MenuAnimation<>(ks, runner);




        String str;
        if (args.length != 0) {
            str = args[0];
        } else {
            str = "level_sets.txt";
        }
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
        List<LevelInformation> list = new ArrayList<>();
        Map<String, List<LevelInformation>> keyToLvlList = new HashMap<>();
        try {
            LineNumberReader lnr = new LineNumberReader(new InputStreamReader(is));
            String line;
            String[] keyToName = null;
            while ((line = lnr.readLine()) != null) {
                if (lnr.getLineNumber() % 2 != 0) {
                    keyToName = line.split(":");
                } else {
                    LevelSpecificationReader spr = new LevelSpecificationReader();
                    InputStream is1 = ClassLoader.getSystemClassLoader().getResourceAsStream(line);
                    BufferedReader bf = new BufferedReader(new InputStreamReader(is1));
                    List<LevelInformation> lvlList = spr.fromReader(bf);
                    keyToLvlList.put(keyToName[0], lvlList);
                    final String lvlListName = keyToName[0];
                    lvlSetMenu.addSelection(keyToName[0], keyToName[1], new Task<Void>() {
                        @Override
                        public Void run() {
                            GameFlow gf = new GameFlow(runner, ks, gui);
                            gf.runLevels(keyToLvlList.get(lvlListName));
                            int score = gf.getScore().getValue();
                            if (hst.getRank(score) <= hst.size()) {
                                DialogManager dialog = gui.getDialogManager();
                                String name = dialog.showQuestionDialog("Name", "What is your name?", "");
                                ScoreInfo si = new ScoreInfo(name, gf.getScore().getValue());
                                hst.add(si);
                                try {
                                    hst.save(file);
                                } catch (IOException e) {
                                    System.out.println("Something went wrong while writing!");
                                }
                            }
                            runner.run(new KeyPressStoppableAnimation(ks, "space", new HighScoresAnimation(hst)));
                            return null;
                        }
                    });
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Wrong file path entered!");
        } catch (IOException e) {
            System.out.println("empty file entered!");
        }






        Task<Void> showHiScoresTask = new Task<Void>() {

            private AnimationRunner ar = runner;
            private Animation highScoresAnimation = new KeyPressStoppableAnimation(ks,
                    "space", new HighScoresAnimation(hst));
            @Override
            public Void run() {
                this.ar.run(this.highScoresAnimation);
                return null;
            }
        };






        Task<Void> exitGame = new Task<Void>() {


            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        };






        MenuAnimation<Task<Void>> menu = new MenuAnimation<>(ks, runner);
        menu.addSelection("h", "see the high scores.", showHiScoresTask);
        menu.addSubMenu("s", "start a new game.", lvlSetMenu);
        menu.addSelection("q", "quit the game.", exitGame);
        while (true) {
            runner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
        }



        //gui.close();


        /*ScoreInfo si = new ScoreInfo("james", 100);
        hst.add(si);
        si = new ScoreInfo("noa", 200);
        hst.add(si);
        si = new ScoreInfo("alon", 300);
        hst.add(si);
        for (ScoreInfo si1 : hst.getHighScores()) {
            System.out.println(si1.getName() + ": " + si1.getScore());
        }


        HighScoresTable hst1 = new HighScoresTable(1);

        HighScoresTable hst2 = HighScoresTable.loadFromFile(file);
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        runner.run(new HighScoresAnimation(hst));
         */
    }
}