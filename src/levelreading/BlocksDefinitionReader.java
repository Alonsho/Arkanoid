package levelreading;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Blocks definition reader.
 */
public class BlocksDefinitionReader {

    /**
     * From reader blocks from symbols factory.
     *
     * @param reader the reader
     * @return the blocks from symbols factory
     * @throws IOException the io exception
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.BufferedReader reader)  throws IOException {
        String defaultSymbol = null;
        int defaultHight = -1;
        int defaultWidth = -1;
        int defaultHitPoints = -1;
        Color defaultFill = null;
        Image defaultFillImg = null;
        Color defaultStroke = null;
        HashMap<Integer, Color> hitToColorMap = null;
        HashMap<Integer, Image> hitToImgMap = null;
        Map<String, BlockCreator> blockCreatorMap = new HashMap<>();
        Map<String, Integer> spacerWidth = new HashMap<>();
        String str = null;
        try {
            str = reader.readLine();
        } catch (IOException e) {
            System.out.println("Something went wrong while reading blocks!");
        }
        List<String> lines = new ArrayList<>();
        lines.add(str);
        while (str != null) {
            try {
                str = reader.readLine();
            } catch (IOException e) {
                System.out.println("Something went wrong while reading blocks!");
            }
            lines.add(str);
        }

        for (String line : lines) {
            if (line == null) {
                break;
            }
            if (line.startsWith("default")) {
                line = line.replace("default ", "");
                String[] defaults = line.split(" ");
                for (String def : defaults) {
                    if (def.startsWith("height")) {
                        defaultHight = Integer.parseInt(def.replace("height:", ""));
                    }
                    if (def.startsWith("width")) {
                        defaultWidth = Integer.parseInt(def.replace("width:", ""));
                    }
                    if (def.startsWith("symbol")) {
                        defaultSymbol = def.replace("symbol:", "");
                    }
                    if (def.startsWith("hit_points")) {
                        defaultHitPoints = Integer.parseInt(def.replace("hit_points:", ""));
                    }
                    if (def.startsWith("fill")) {
                        if (def.startsWith("fill:color")) {
                            def = def.replace("fill:color", "");
                            defaultFill = BlocksDefinitionReader.parseColor(def);
                        } else if (def.startsWith("fill:image")) {
                            def = def.replace("fill:image", "");
                            def = def.replace("(", "");
                            def = def.replace(")", "");
                            try {
                                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(def);
                                defaultFillImg = ImageIO.read(is);
                            } catch (IOException e) {
                                System.out.println("Something went wrong while reading blocks!");
                            }
                        }
                    }
                    if (def.startsWith("stroke")) {
                        defaultStroke = BlocksDefinitionReader.parseColor(def.replace("stroke:color", ""));
                    }
                }
            }
            if (line.startsWith("bdef")) {
                hitToColorMap = new HashMap<>();
                hitToImgMap = new HashMap<>();
                String actualSymbol = defaultSymbol;
                int actualHight = defaultHight;
                int actualWidth = defaultWidth;
                int actualHitPoints = defaultHitPoints;
                Color actualFill = defaultFill;
                Image actualFillImg = defaultFillImg;
                Color actualStroke = defaultStroke;

                line = line.replace("bdef ", "");
                String[] defaults = line.split(" ");
                for (String def : defaults) {
                    if (def.startsWith("height")) {
                        actualHight = Integer.parseInt(def.replace("height:", ""));
                    }
                    if (def.startsWith("width")) {
                        actualWidth = Integer.parseInt(def.replace("width:", ""));
                    }
                    if (def.startsWith("symbol")) {
                        actualSymbol = def.replace("symbol:", "");
                    }
                    if (def.startsWith("hit_points")) {
                        actualHitPoints = Integer.parseInt(def.replace("hit_points:", ""));
                    }
                    if (def.startsWith("stroke")) {
                        actualStroke = BlocksDefinitionReader.parseColor(def.replace("stroke:color", ""));
                    }
                }
                for (String def : defaults) {
                    if (def.startsWith("fill")) {
                        if (def.startsWith("fill:color")) {
                            def = def.replace("fill:color", "");
                            actualFill = BlocksDefinitionReader.parseColor(def);
                        } else if (def.startsWith("fill:image")) {
                            def = def.replace("fill:image", "");
                            def = def.replace("(", "");
                            def = def.replace(")", "");
                            try {
                                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(def);
                                actualFillImg = ImageIO.read(is);
                            } catch (IOException e) {
                                System.out.println("Something went wrong while reading blocks!");
                            }
                        }
                    }
                    for (int i = 1; i <= actualHitPoints; i++) {
                        if (def.startsWith("fill-")) {
                            addToMap(def, i, hitToColorMap, hitToImgMap);
                        }
                    }
                }
                if (actualHight == -1 || actualWidth == -1 || actualHitPoints == -1 || actualSymbol == null
                        || (hitToColorMap.size() == 0 && hitToImgMap.size() == 0 && actualFill == null
                        && actualFillImg == null)) {
                    throw new IOException();
                }
                int[]  attributes = {actualHight, actualWidth, actualHitPoints};
                BasicBlockCreator basicblockcreator = new BasicBlockCreator(attributes,
                        hitToColorMap, hitToImgMap, actualStroke, actualFill, actualFillImg);
                blockCreatorMap.put(actualSymbol, basicblockcreator);
            }
            if (line.startsWith("sdef")) {
                String symbol = null;
                Integer widthOfSymbol = null;
                String[] parts = line.split(" ");
                for (String part : parts) {
                    if (part.startsWith("symbol:")) {
                        symbol = part.replace("symbol:", "");
                    }
                    if (part.startsWith("width:")) {
                        widthOfSymbol = Integer.parseInt(part.replace("width:", ""));
                    }
                }
                spacerWidth.put(symbol, widthOfSymbol);
            }
        }
        return new BlocksFromSymbolsFactory(spacerWidth, blockCreatorMap);
    }

    /**
     * Parse color color.
     *
     * @param def the def
     * @return the color
     */
    public static Color parseColor(String def) {
        if (def.startsWith("(RGB")) {
            String[] colors = def.split(",");
            colors[0] = colors[0].replace("(RGB(", "");
            colors[2] = colors[2].replace("))", "");
            return new Color(Integer.parseInt(colors[0]), Integer.parseInt(colors[1]), Integer.parseInt(colors[2]));
        } else {
            def = def.replace("(", "");
            def = def.replace(")", "");
            Color color;
            try {
                Field field = Class.forName("java.awt.Color").getField(def);
                color = (Color) field.get(null);
            } catch (Exception e) {
                color = null;
            }
            return color;
        }
    }

    /**
     * Add to map.
     *
     * @param def           the def
     * @param i             the
     * @param hitToColorMap the hit to color map
     * @param hitToImgMap   the hit to img map
     */
    public static void addToMap(String def, int i, HashMap<Integer, Color> hitToColorMap,
                                HashMap<Integer, Image> hitToImgMap) {
        if (def.startsWith("fill-" + i + ":color")) {
            def = def.replace("fill-" + i + ":color", "");
            hitToColorMap.put(i, BlocksDefinitionReader.parseColor(def));
        } else if (def.startsWith("fill-" + i + ":image")) {
            def = def.replace("fill-" + i + ":image", "");
            def = def.replace("(", "");
            def = def.replace(")", "");
            try {
                InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(def);
                hitToImgMap.put(i, ImageIO.read(is));
            } catch (IOException e) {
                System.out.println("Something went wrong while reading blocks!");
            }
        }
    }
}
