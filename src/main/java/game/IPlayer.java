package game;

/**
 * Created by houssem89 on 07/11/2019.
 */
public interface IPlayer {
    /**
     * Increment score.
     *
     * @return the boolean
     */
    public boolean incrementScore();

    /**
     * Gets name player.
     *
     * @return the name player
     */
    public String getNamePlayer();

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore();

    /**
     * Sets score.
     *
     * @param value the value
     * @return the score
     */
    public int setScore(int value);
}
