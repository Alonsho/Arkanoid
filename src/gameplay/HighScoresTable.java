package gameplay;


import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * The type High scores table.
 */
public class HighScoresTable {
    private int size;
    private List<ScoreInfo> list;


    /**
     * Instantiates a new High scores table.
     *
     * @param size the size
     */
// Create an empty high-scores table with the specified size.
    // The size means that the table holds up to size top scores.
    public HighScoresTable(int size) {
        this.size = size;
        this.list = new ArrayList<>();
    }

    /**
     * Add.
     *
     * @param score the score
     */
// Add a high-score.
    public void add(ScoreInfo score) {

        this.list.add(getRank(score.getScore()) - 1, score);
        if (this.list.size() > this.size) {
            this.list.remove(list.get(this.list.size() - 1));
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
// Return table size.
    public int size() {
        return this.size;
    }

    /**
     * Gets high scores.
     *
     * @return the high scores
     */
// Return the current high scores.
    // The list is sorted such that the highest
    // scores come first.
    public List<ScoreInfo> getHighScores() {
        return this.list;
    }

    /**
     * Gets rank.
     *
     * @param score the score
     * @return the rank
     */
// return the rank of the current score: where will it
    // be on the list if added?
    // Rank 1 means the score will be highest on the list.
    // Rank `size` means the score will be lowest.
    // Rank > `size` means the score is too low and will not
    //      be added to the list.
    public int getRank(int score) {
        for (int i = 0; i < this.list.size(); i++) {
            if (score > this.list.get(i).getScore()) {
                return i + 1;
            }
        }
        return this.list.size() + 1;
    }

    /**
     * Clear.
     */
// Clears the table
    public void clear() {
        this.list = new ArrayList<>();
    }

    /**
     * Load.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Load table data from file.
    // Current table data is cleared.
    public void load(File filename) throws IOException {
        BufferedReader  is = null;
        try {
            is = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                String[] str = line.split(": ");
                ScoreInfo si = new ScoreInfo(str[0], Integer.parseInt(str[1]));
                this.add(si);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * Save.
     *
     * @param filename the filename
     * @throws IOException the io exception
     */
// Save table data to the specified file.
    public void save(File filename) throws IOException {
        PrintWriter os = null;
        try {
            os = new PrintWriter(new OutputStreamWriter(new FileOutputStream(filename)));
            for (ScoreInfo si : this.list) {
                os.println(si.getName() + ": " + si.getScore());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while writing!");
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }

    /**
     * Load from file high scores table.
     *
     * @param filename the filename
     * @return the high scores table
     */
// Read a table from file and return it.
    // If the file does not exist, or there is a problem with
    // reading it, an empty table is returned.
    public static HighScoresTable loadFromFile(File filename) {
        HighScoresTable hst = new HighScoresTable(10);
        BufferedReader is = null;
        try {
            is = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
            String line;
            while ((line = is.readLine()) != null) {
                String[] str = line.split(": ");
                ScoreInfo si = new ScoreInfo(str[0], Integer.parseInt(str[1]));
                hst.add(si);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading!");
            return new HighScoresTable(10);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("Failed closing the file!");
                }
            }
        }
        return hst;
    }
}
