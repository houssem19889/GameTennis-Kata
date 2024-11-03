package game.Impl;

import game.Exception.EnterScoreException;
import game.IScore;


/**
 * The type Set score.
 */
public class SetScore implements IScore {
    private final static int MAX_SCORE = 7;
    private int setScore;

    @Override
    public boolean incrementScore() {
        if (setScore >= MAX_SCORE) {
            return false;
        }
        setScore++;
        return true;
    }

    /**
     * Force increment.
     *
     * @return the boolean
     */
    public boolean forceIncrement() {
        setScore++;
        return true;
    }

    @Override
    public int getCurrentScore() {
        return setScore;
    }

    @Override
    public int setScore(int score) {
        return 0;
    }

    @Override
    public void resetScore() throws EnterScoreException {
        throw new EnterScoreException(" method should not be used for this object.");
    }
}
