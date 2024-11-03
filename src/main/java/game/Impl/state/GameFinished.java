package game.Impl.state;

import game.Impl.Player;
import game.Impl.RuleResultImpl;
import game.utilEnum.ResultType;


/**
 * The type Game finished.
 */
public class GameFinished extends GameRule {

    /**
     * Instantiates a new Game finished.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     */
    public GameFinished(Player firstPlayer, Player secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    @Override
    public boolean canBeApplied() {
        boolean canBeApplied = getFirstPlayer().getScore() > GameRule.MAX_SCORE || getSecondPlayer().getScore() > MAX_SCORE;
        this.ruleResult = getFirstPlayer().getScore() > getSecondPlayer().getScore()
                ? new RuleResultImpl(ResultType.FINISHED, getFirstPlayer())
                : new RuleResultImpl(ResultType.FINISHED, getSecondPlayer());
        return canBeApplied;
    }

    @Override
    public ResultType getResult() {
        return null;
    }
}
