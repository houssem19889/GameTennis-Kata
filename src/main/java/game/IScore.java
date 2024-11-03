package game;

import game.Exception.EnterScoreException;


/**
 * The interface Score.
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
     *
     * @throws EnterScoreException the enter score exception
     */
    public void resetScore() throws EnterScoreException;
}
