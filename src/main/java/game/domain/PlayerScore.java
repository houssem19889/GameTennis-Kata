package game.domain;

import game.Impl.Player;
import game.Impl.SetScore;

/**
 * The type Player score.
 */
public class PlayerScore {
    private final Player player;
    private final SetScore score;

    /**
     * Instantiates a new Player score.
     *
     * @param player the player
     */
    public PlayerScore(Player player) {
        this.player = player;
        this.score = new SetScore();
    }

    /**
     * Gets player.
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public SetScore getScore() {
        return score;
    }

    /**
     * Increment score.
     */
    public void incrementScore() {
        score.incrementScore();
    }
}
