package game.Impl;

import game.IGame;
import game.IRule;
import game.Impl.state.Advantage;
import game.Impl.state.Deuce;
import game.Impl.state.GameFinished;
import game.utilEnum.ResultType;
import game.utilEnum.WinPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by houssem89 on 07/11/2019.
 */
public class Game implements IGame<Player> {
    private static final int MAX_SCORE = 40;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private WinPlayer winPlayer;
    private List<IRule> rules = new ArrayList<>();

    private Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer, "should be not null firstPlayer");
        this.secondPlayer = Objects.requireNonNull(secondPlayer, "should be not null secondPlayer");
        this.winPlayer = WinPlayer.NONE;
        this.rules.add(new Advantage(firstPlayer, secondPlayer));
        this.rules.add(new Deuce(firstPlayer, secondPlayer));
        this.rules.add(new GameFinished(firstPlayer, secondPlayer));
    }

    /**
     * Between game.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     * @return the game
     */
    public static Game between(Player firstPlayer, Player secondPlayer) {

        return new Game(firstPlayer, secondPlayer);
    }

    /**
     * Between game.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     * @return the game
     */
    public static Game between(String firstPlayer, String secondPlayer) {
        Player one = new Player(firstPlayer);
        Player two = new Player(secondPlayer);
        return new Game(one, two);
    }

    @Override
    public boolean incrementFirstPlayer() {
        if (isFinished()) {
            return false;
        }
        // return to deuce game
        if (secondPlayer.isHasAdvantage()) {
            secondPlayer.setHasAdvantage(false);
            return true;
        }
        // player gets advantage
        if (isDeuce()) {
            firstPlayer.setHasAdvantage(true);
            secondPlayer.setHasAdvantage(false);
            return true;
        }
        // game won
        if (firstPlayer.getScore() == MAX_SCORE || firstPlayer.isHasAdvantage()) {
            winPlayer = WinPlayer.First_Player;
            return false;
        }
        return firstPlayer.incrementScore();


    }

    /**
     * Game score.
     *
     * @param initialScore the initial score
     * @return the game
     */

    public Game gameScore(String initialScore) {
        int scoreFirst = firstPlayerScore();
        int secondScore = secondPlayerScore();
        if (initialScore.equals("Deuce")) {
            scoreFirst = 40;
            scoreFirst = 40;
        } else {
            String[] scores = initialScore.split(" ");
            firstPlayer.setScore(Integer.parseInt(scores[0]));
            secondPlayer.setScore(Integer.parseInt(scores[1]));
        }
        return new Game(firstPlayer, secondPlayer);
    }

    /**
     * Score string.
     *
     * @return the string
     */
    public String score() {
        if (aPlayAfterPoint()) {
            ResultType resultType = ResultType.of(this.firstPlayerScore(), this.secondPlayerScore());
            return String.valueOf(resultType.label);
        }
        return firstPlayerScore() + " " + secondPlayerScore();
    }

    private boolean aPlayAfterPoint() {
        return (this.firstPlayerScore() >= 40 && this.secondPlayerScore() >= 40) || this.firstPlayerScore() > 40 || this.secondPlayerScore() > 40 || (this.firstPlayerScore() < 40 && this.secondPlayerScore() >= 40) || (this.firstPlayerScore() >= 40 && this.secondPlayerScore() < 40);
    }

    @Override
    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (firstPlayer.isHasAdvantage()) {
            firstPlayer.setHasAdvantage(false);
            return true;
        }
        if (isDeuce()) {
            secondPlayer.setHasAdvantage(true);
            firstPlayer.setHasAdvantage(false);
            return true;
        }
        if (secondPlayer.getScore() == MAX_SCORE || secondPlayer.isHasAdvantage()) {
            winPlayer = WinPlayer.Second_Player;
            return false;
        }

        return secondPlayer.incrementScore();
    }

    /**
     * Is deuce.
     *
     * @return the boolean
     */
    public boolean isDeuce() {
        if (firstPlayer.isHasAdvantage() || secondPlayer.isHasAdvantage()) {
            return false;
        }
        return (firstPlayer.getScore() == MAX_SCORE && secondPlayer.getScore() == MAX_SCORE);
    }

    @Override
    public boolean isFinished() {
        return !winPlayer.equals(WinPlayer.NONE);
    }

    @Override
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    @Override
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    @Override
    public int firstPlayerScore() {
        return firstPlayer.getScore();
    }

    @Override
    public int secondPlayerScore() {
        return secondPlayer.getScore();
    }

    @Override
    public WinPlayer getWinnerPlayer() {
        return winPlayer;
    }

    /**
     * Reset void.
     */
    void reset() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();
        winPlayer = WinPlayer.NONE;
    }
}
