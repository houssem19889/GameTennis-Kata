package game.Impl;

import game.IPlayer;

import java.util.Objects;

/**
 * Created by houssem89 on 07/11/2019.
 */
public class Player implements IPlayer {
    private final String namePlayer;
    /**
     * The Has advantage.
     */
    public boolean hasAdvantage;
    private Score score;

    /**
     * Instantiates a new Player.
     *
     * @param namePlayer the name player
     */
    public Player(String namePlayer) {
        this.namePlayer = Objects.requireNonNull(namePlayer, "should be not null");
        this.score = new Score();
    }

    /**
     * Is has advantage.
     *
     * @return the boolean
     */
    public boolean isHasAdvantage() {
        return hasAdvantage;
    }

    /**
     * Sets has advantage.
     *
     * @param hasAdvantage the has advantage
     */
    public void setHasAdvantage(boolean hasAdvantage) {
        this.hasAdvantage = hasAdvantage;
    }

    @Override
    public boolean incrementScore() {
        return score.incrementScore();
    }

    @Override
    public String getNamePlayer() {
        return namePlayer;
    }

    @Override
    public int getScore() {
        return score.getCurrentScore();
    }

    @Override
    public int setScore(int value) {
        return score.setScore(value);
    }

    /**
     * Reset score.
     */
    void resetScore() {
        score.resetScore();
    }
}
