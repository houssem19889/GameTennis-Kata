package game.Impl;

import game.IGame;
import game.IRule;
import game.Impl.state.Advantage;
import game.Impl.state.Deuce;
import game.Impl.state.GameFinished;
import game.utilEnum.ResultType;
import game.utilEnum.WinPlayer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * The type Game.
 */
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class Game implements IGame<Player> {
    private static final int MAX_SCORE = 40;
    private final Player firstPlayer;
    private final Player secondPlayer;
    private WinPlayer winPlayer;
    private List<IRule> rules = new ArrayList<>();

    /**
     * Instantiates a new Game.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     */
    public Game(Player firstPlayer, Player secondPlayer ) {
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
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @return the game
     */
    public static Game between(Player firstPlayer, Player secondPlayer) {

        return new Game(firstPlayer, secondPlayer);
    }

    /**
     * Between game.
     *
     * @param firstPlayer  the first player
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
        if (initialScore.equals("Deuce")) {
            firstPlayer.setScore(40);
            secondPlayer.setScore(40);
        } else {
            String[] scores = initialScore.split(" ");
            firstPlayer.setScore(Integer.parseInt(scores[0]));
            secondPlayer.setScore(Integer.parseInt(scores[1]));
        }
        return new Game(firstPlayer, secondPlayer);
    }

    /**
     * Returns the current score as a string.
     *
     * @return the formatted score string
     */
    public String score() {
        if (isAdvantageOrWinPoint()) {
            ResultType resultType = ResultType.of(firstPlayerScore(), secondPlayerScore());
            return resultType.label;
        }
        return firstPlayerScore() + " " + secondPlayerScore();
    }

    /**
     * Checks if the score is at a point where players are beyond 40 or both are at deuce.
     *
     * @return true if the game is in a play-after point state
     */
    private boolean isAdvantageOrWinPoint() {
        return (firstPlayerScore() >= 40 && secondPlayerScore() >= 40) ||
                firstPlayerScore() > 40 ||
                secondPlayerScore() > 40;
    }

    @Override
    public boolean incrementSecondPlayer() {
        if (isFinished()) {
            return false;
        }
        if (firstPlayer.isHasAdvantage()) {
            firstPlayer.setHasAdvantage(false);
        } else if (isDeuce()) {
            secondPlayer.setHasAdvantage(true);
            firstPlayer.setHasAdvantage(false);
        } else if (secondPlayer.getScore() == MAX_SCORE || secondPlayer.isHasAdvantage()) {
            winPlayer = WinPlayer.Second_Player;
            return false;
        } else {
            return secondPlayer.incrementScore();
        }
        return true;
    }

    /**
     * Checks if the game is in a deuce state.
     *
     * @return true if both players are at deuce and neither has advantage
     */
    public boolean isDeuce() {
        return !firstPlayer.isHasAdvantage() && !secondPlayer.isHasAdvantage() &&
                firstPlayer.getScore() == MAX_SCORE && secondPlayer.getScore() == MAX_SCORE;
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
     * Resets the game state.
     */
    void reset() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();
        winPlayer = WinPlayer.NONE;
    }

}
