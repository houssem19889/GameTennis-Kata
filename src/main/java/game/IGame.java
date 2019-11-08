package game;

import game.Impl.Player;
import game.utilEnum.WinPlayer;

import java.util.Optional;

/**
 * Created by houssem89 on 07/11/2019.
 *
 * @param <E>  the type parameter
 */
public interface IGame<E extends Player> {
    /**
     * Increment first player.
     *
     * @return the boolean
     */
    boolean incrementFirstPlayer();

    /**
     * Increment second player.
     *
     * @return the boolean
     */
    boolean incrementSecondPlayer();

    /**
     * Is finished.
     *
     * @return the boolean
     */
    boolean isFinished();

    /**
     * Gets first player.
     *
     * @return the first player
     */
    E getFirstPlayer();

    /**
     * Gets second player.
     *
     * @return the second player
     */
    E getSecondPlayer();

    /**
     * First player score.
     *
     * @return the int
     */
    int firstPlayerScore();

    /**
     * Second player score.
     *
     * @return the int
     */
    int secondPlayerScore();

    /**
     * Gets winner player.
     *
     * @return the winner player
     */
    public WinPlayer getWinnerPlayer();

    /**
     * Gets winner.
     *
     * @return the winner
     */
    default Optional<IPlayer> getWinner() {
        if (getWinnerPlayer().equals(WinPlayer.NONE)) {
            return Optional.empty();
        }
        return getWinnerPlayer().equals(WinPlayer.First_Player) ?
                Optional.of(getFirstPlayer()) :
                Optional.of(getSecondPlayer());
    }
}
