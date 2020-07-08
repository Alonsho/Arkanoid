package levelreading;

import biuoop.DrawSurface;
import gameplay.Velocity;
import levels.LevelInformation;
import sprites.Ball;
import sprites.Block;
import sprites.Sprite;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Level specification reader.
 */
public class LevelSpecificationReader {
    private boolean reachedEOF = false;

    /**
     * From reader list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<LevelInformation> fromReader(java.io.BufferedReader reader) {
        List<LevelInformation> liList = new ArrayList<>();
        List<String> stringList;
        LevelInformation li = null;

        while (!this.reachedEOF) {
            stringList = readLevel(reader);
            if (stringList != null) {
                try {
                    li = createLevelInformation(stringList);
                    liList.add(li);
                } catch (IOException e) {
                    System.out.println("Something went wrong while reading from levels file!");
                    System.exit(1);
                }
            }
        }




        return liList;
    }

    /**
     * Read level list.
     *
     * @param reader the reader
     * @return the list
     */
    public List<String> readLevel(java.io.BufferedReader reader) {
        List<String> stringList = new ArrayList<>();
        String line = null;


        try {
            line = reader.readLine();
            if (line == null) {
                reachedEOF = true;
                return null;
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
        }
        while (!line.equals("START_LEVEL")) {
            try {
                line = reader.readLine();
                if (line == null) {
                    reachedEOF = true;
                    return null;
                }
            } catch (IOException e) {
                System.out.println("Something went wrong while reading!");
            }
        }
        try {
            line = reader.readLine();
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
        }
        while (!line.equals("END_LEVEL")) {
            stringList.add(line);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("Something went wrong while reading!");
            }
        }
        return stringList;
    }


    /**
     * Create level information level information.
     *
     * @param stringList the string list
     * @return the level information
     * @throws IOException the io exception
     */
    public LevelInformation createLevelInformation(List<String> stringList) throws IOException {
        boolean isReadingBlocks = false;
        Map<String, String> map = new HashMap<String, String>();
        for (String str : stringList) {
            if (str.equals("START_BLOCKS")) {
                isReadingBlocks = true;
            }
            if (!isReadingBlocks) {
                if (str.contains(":")) {
                    String[] couple = str.split(":");
                    map.put(couple[0], couple[1]);
                }
            }
        }
        if (!map.containsKey("level_name") || !map.containsKey("ball_velocities") || !map.containsKey("background")
                || !map.containsKey("paddle_speed") || !map.containsKey("paddle_width")
                || !map.containsKey("block_definitions") || !map.containsKey("blocks_start_x")
                || !map.containsKey("blocks_start_y") || !map.containsKey("row_height")
                || !map.containsKey("num_blocks")
                || !stringList.contains("START_BLOCKS") || !stringList.contains("END_BLOCKS")) {
            throw new IOException();
        }
        LevelInformation li = new LevelInformation() {
            @Override
            public int numberOfBalls() {
                return this.initialBallVelocities().size();
            }
            @Override
            public List<Velocity> initialBallVelocities() {
                List<Velocity> velocityList = new ArrayList<>();
                String[] velocities = map.get("ball_velocities").split(" ");
                for (int i = 0; i < velocities.length; i++) {
                    String[] velString = velocities[i].split(",");
                  double angle = Double.parseDouble(velString[0]);
                  double speed = Double.parseDouble(velString[1]);
                  velocityList.add(Velocity.fromAngleAndSpeed(angle, speed));
                }
                return velocityList;
            }
            @Override
            public int paddleSpeed() {
                return Integer.parseInt(map.get("paddle_speed"));
            }
            @Override
            public int paddleWidth() {
                return Integer.parseInt(map.get("paddle_width"));
            }
            @Override
            public String levelName() {
                return map.get("level_name");
            }
            @Override
            public Sprite getBackground() {
                Sprite bg = new Sprite() {
                    private Image backgroundImage = null;
                    @Override
                    public void drawOn(DrawSurface d) {
                        String str = map.get("background");
                        if (str.startsWith("color")) {
                            str = str.replace("color", "");
                            if (str.startsWith("(RGB")) {
                                String[] colors = str.split(",");
                                colors[0] = colors[0].replace("(RGB(", "");
                                colors[2] = colors[2].replace("))", "");
                                d.setColor(new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]),
                                        Integer.parseInt(colors[2])));
                                d.fillRectangle(25, 45, d.getWidth() - 49, d.getHeight() - 20);
                            } else {
                                str = str.replace("(", "");
                                str = str.replace(")", "");
                                d.setColor(BlocksDefinitionReader.parseColor(str));
                                d.fillRectangle(25, 45, d.getWidth() - 49, d.getHeight() - 20);
                            }
                        } else {
                            backgroundImage = getBackgroundImage(str, backgroundImage);
                            d.drawImage(25, 45, backgroundImage);
                        }
                    }

                    @Override
                    public void timePassed() {
                        return;
                    }
                };
                return bg;
            }

            @Override
            public List<Block> blocks() {
                BlocksFromSymbolsFactory blockCreator = null;
                String str = map.get("block_definitions");
                int xLoc = Integer.parseInt(map.get("blocks_start_x"));
                int yLoc = Integer.parseInt(map.get("blocks_start_y"));
                int rowHeight = Integer.parseInt(map.get("row_height"));
                List<Block> blockList = new ArrayList<>();
                Block block;
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    blockCreator = BlocksDefinitionReader.fromReader(reader);
                    int i = 0;
                    while (!stringList.get(i).equals("START_BLOCKS")) {
                        i++;
                    }
                    i++;
                    String blockLine = stringList.get(i);
                    while (!blockLine.equals("END_BLOCKS")) {
                        String[] symbols = blockLine.split("");
                        for (String sym : symbols) {
                            if (blockCreator.isBlockSymbol(sym)) {
                                block = blockCreator.getBlock(sym, xLoc, yLoc);
                                blockList.add(block);
                                xLoc += block.getWidth();
                            } else if (blockCreator.isSpaceSymbol(sym)) {
                                xLoc += blockCreator.getSpaceWidth(sym);
                            }
                        }
                        i++;
                        blockLine = stringList.get(i);
                        yLoc += rowHeight;
                        xLoc = Integer.parseInt(map.get("blocks_start_x"));
                    }
                } catch (IOException e) {
                    System.out.println("Something went wrong while reading blocks!");
                    System.exit(1);
                }
                return blockList;
            }

            @Override
            public List<Ball> balls() {
                List<Ball> balls = new ArrayList<>();
                List<Velocity> velocities = initialBallVelocities();
                for (int i = 0; i < velocities.size(); i++) {
                    Ball ball = new Ball(400, 570, 6, Color.white);
                    ball.setVelocity(velocities.get(i));
                     balls.add(ball);
                }
                return balls;
            }

            @Override
            public int numberOfBlocksToRemove() {
                return Integer.parseInt(map.get("num_blocks"));
            }
        };
        return li;
    }

    /**
     * Gets background image.
     *
     * @param str             the str
     * @param backgroundImage the background image
     * @return the background image
     */
    public Image getBackgroundImage(String str, Image backgroundImage) {
        if (backgroundImage == null) {
            str = str.replace("image(", "");
            str = str.replace(")", "");
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(str);
                backgroundImage = ImageIO.read(is);

            } catch (IOException e) {
                System.out.println("Something went wrong while reading background image!");
                System.exit(1);
            }
        }
        return backgroundImage;
    }
}
