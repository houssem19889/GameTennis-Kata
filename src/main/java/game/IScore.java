package game;

/**
 * Created by houssem89 on 07/11/2019.
 */
public interface IScore {
    /**
     * Increment score.
     *
     * @return the boolean
     */
    public boolean incrementScore();

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public int getCurrentScore();

    /**
     * Sets score.
     *
     * @param score the score
     * @return the score
     */
    public int setScore(int score);

    /**
     * Reset score.
     */
    public void resetScore();
}
