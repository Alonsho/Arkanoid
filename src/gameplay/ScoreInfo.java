package gameplay;

/**
 * The type Score info.
 */
public class ScoreInfo implements Comparable<ScoreInfo> {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * compares two scores.
     *
     * @param other         the other score
     *
     * @return the bigger score (positive or negative)
     */
    public int compareTo(ScoreInfo other) {
        return other.score - this.score;
    }
}
