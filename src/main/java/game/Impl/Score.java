package game.Impl;

import game.Exception.EnterScoreException;
import game.IScore;

/**
 * Created by houssem89 on 07/11/2019.
 */
public class Score implements IScore {
    private int score;

    /**
     * Instantiates a new Score.
     */
    public Score() {
        this.score = 0;
    }

    /**
     * Instantiates a new Score.
     *
     * @param score the score
     * @throws EnterScoreException the enter score exception
     */
    public Score(int score) throws EnterScoreException {
        if (score != 0 && score != 15 && score != 30 && score != 40) {
            throw new EnterScoreException("Illegal score: " + score);

        } else {
            switch (score) {
                case 0:
                    score = 0;
                case 1:
                    score = 15;
                case 2:
                    score = 30;
                case 3:
                    score = 40;
            }
            this.score = score;
        }

    }

    public boolean incrementScore() {
        if (score >= 40) {
            return false;
        }
        if (score == 0 || score == 15) {
            score += 15;
        } else if (score == 30) {
            score = 40;
        }
        return true;
    }

    /**
     * Force increment.
     *
     * @return the boolean
     */
    public boolean forceIncrement() {
        score++;
        return true;
    }

    @Override
    public int getCurrentScore() {
        return score;
    }

    @Override
    public int setScore(int score) {
        return this.score = score;
    }

    @Override
    public void resetScore() {
        score = 0;
    }


}
