package game.Impl.state;

import game.Impl.Player;
import game.Impl.RuleResultImpl;
import game.utilEnum.ResultType;

/**
 * Created by houssem89 on 08/11/2019.
 */
public class Deuce extends GameRule {
    /**
     * Instantiates a new Deuce.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     */
    public Deuce(Player firstPlayer, Player secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    @Override
    public boolean canBeApplied() {
        if (getFirstPlayer().isHasAdvantage() || getSecondPlayer().isHasAdvantage()) {
            return false;
        }
        this.ruleResult = new RuleResultImpl(ResultType.DEUCE, null);
        return (getFirstPlayer().getScore() == MAX_SCORE && getSecondPlayer().getScore() == MAX_SCORE);
    }

    @Override
    public ResultType getResult() {
        return null;
    }
}
