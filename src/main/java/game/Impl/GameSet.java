package game.Impl;

import game.IGame;
import game.utilEnum.WinPlayer;
import javafx.util.Pair;

/**
 * Created by houssem89 on 08/11/2019.
 */
public class GameSet implements IGame {
    private final Pair<Player, SetScore> firstPlayer;
    private final Pair<Player, SetScore> secondPlayer;
    private WinPlayer winPlayer;
    private boolean tieBreak;

    private GameSet(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = new Pair<Player, SetScore>(firstPlayer, new SetScore());
        this.secondPlayer = new Pair<Player, SetScore>(secondPlayer, new SetScore());
        this.winPlayer = WinPlayer.NONE;
    }

    /**
     * Between game set.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     * @return the game set
     */
    public static GameSet between(Player firstPlayer, Player secondPlayer) {

        return new GameSet(firstPlayer, secondPlayer);
    }

    /**
     * Between game set.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     * @return the game set
     */
    public static GameSet between(String firstPlayer, String secondPlayer) {
        Player one = new Player(firstPlayer);
        Player two = new Player(secondPlayer);
        return new GameSet(one, two);
    }

    private static boolean tieBreakRule(Pair<Player, SetScore> scoringPlayer,
                                        Pair<Player, SetScore> opponent,
                                        Runnable winAction) {
        if (scoringPlayer.getValue().getCurrentScore() == (opponent.getValue().getCurrentScore() + 1)) {
            winAction.run();
            return false;
        }
        return scoringPlayer.getValue().forceIncrement();
    }

    private static boolean isWinning(Pair<Player, SetScore> player, Pair<Player, SetScore> opponent) {
        return opponent.getValue().getCurrentScore() <= 4 && player.getValue().getCurrentScore() == 5;
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
            return firstPlayer.getValue().incrementScore();
        }
        if (isWinning(firstPlayer, secondPlayer)) {
            firstPlayerWins();
            return false;
        }
        return firstPlayer.getValue().incrementScore();
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
            return secondPlayer.getValue().incrementScore();
        }
        if (isWinning(secondPlayer, firstPlayer)) {
            secondPlayerWins();
            return false;
        }
        return secondPlayer.getValue().incrementScore();
    }

    private void firstPlayerWins() {
        firstPlayer.getValue().incrementScore();
        winPlayer = WinPlayer.First_Player;
    }

    private void secondPlayerWins() {
        secondPlayer.getValue().incrementScore();
        winPlayer = WinPlayer.Second_Player;
    }

    @Override
    public boolean isFinished() {
        return !winPlayer.equals(WinPlayer.NONE);
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer.getKey();
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer.getKey();
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getValue().getCurrentScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getValue().getCurrentScore();
    }

    @Override
    public WinPlayer getWinnerPlayer() {
        return winPlayer;
    }
}
