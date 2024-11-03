package game.Impl;

import game.IGame;
import game.domain.PlayerScore;
import game.utilEnum.WinPlayer;
import javafx.util.Pair;


/**
 * The type Game set.
 */
public class GameSet implements IGame {
    private final PlayerScore firstPlayer;
    private final PlayerScore secondPlayer;
    private WinPlayer winPlayer;
    private boolean tieBreak;

    private GameSet(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = new PlayerScore(firstPlayer);
        this.secondPlayer = new PlayerScore(secondPlayer);
        this.winPlayer = WinPlayer.NONE;
    }

    /**
     * Between game set.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return the game set
     */
    public static GameSet between(Player firstPlayer, Player secondPlayer) {

        return new GameSet(firstPlayer, secondPlayer);
    }

    /**
     * Between game set.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return the game set
     */
    public static GameSet between(String firstPlayer, String secondPlayer) {
        Player one = new Player(firstPlayer);
        Player two = new Player(secondPlayer);
        return new GameSet(one, two);
    }

    private static boolean tieBreakRule(PlayerScore scoringPlayer,
                                        PlayerScore opponent,
                                        Runnable winAction) {
        if (scoringPlayer.getScore().getCurrentScore() == (opponent.getScore().getCurrentScore() + 1)) {
            winAction.run();
            return false;
        }
        return scoringPlayer.getScore().forceIncrement();
    }

    private static boolean isWinning(PlayerScore player, PlayerScore opponent) {
        return opponent.getScore().getCurrentScore() <= 4 && player.getScore().getCurrentScore() == 5;
    }

    @Override
    public boolean incrementFirstPlayer() {
        if (isFinished()) {
            return false;
        }
        if (tieBreak) {
            return tieBreakRule(firstPlayer, secondPlayer, this::firstPlayerWins);
        }
        if (firstPlayerScore() == 5 && secondPlayerScore() == 6) {
            tieBreak = true;
            return firstPlayer.getScore().incrementScore();
        }
        if (isWinning(firstPlayer, secondPlayer)) {
            firstPlayerWins();
            return false;
        }
        return firstPlayer.getScore().incrementScore();
    }

    @Override
    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (tieBreak) {
            return tieBreakRule(secondPlayer, firstPlayer, this::secondPlayerWins);
        }
        if (firstPlayerScore() == 6 && secondPlayerScore() == 5) {
            tieBreak = true;
            return secondPlayer.getScore().incrementScore();
        }
        if (isWinning(secondPlayer, firstPlayer)) {
            secondPlayerWins();
            return false;
        }
        return secondPlayer.getScore().incrementScore();
    }

    private void firstPlayerWins() {
        firstPlayer.getScore().incrementScore();
        winPlayer = WinPlayer.First_Player;
    }

    private void secondPlayerWins() {
        secondPlayer.getScore().incrementScore();
        winPlayer = WinPlayer.Second_Player;
    }

    @Override
    public boolean isFinished() {
        return !winPlayer.equals(WinPlayer.NONE);
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer.getPlayer();
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer.getPlayer();
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getScore().getCurrentScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getScore().getCurrentScore();
    }

    @Override
    public WinPlayer getWinnerPlayer() {
        return winPlayer;
    }
}
