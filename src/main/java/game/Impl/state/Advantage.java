package game.Impl.state;

import game.Impl.Player;
import game.Impl.RuleResultImpl;
import game.utilEnum.ResultType;

/**
 * Created by houssem89 on 08/11/2019.
 */
public class Advantage extends GameRule {
    /**
     * Instantiates a new Advantage.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     */
    public Advantage(Player firstPlayer, Player secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    @Override
    public boolean canBeApplied() {
        boolean canBeApplied = getFirstPlayer().getScore() == GameRule.MAX_SCORE || getSecondPlayer().getScore() == GameRule.MAX_SCORE;
        this.ruleResult = getFirstPlayer().getScore() == GameRule.MAX_SCORE
                ? new RuleResultImpl(ResultType.ADVANTAGE, getFirstPlayer())
                : new RuleResultImpl(ResultType.ADVANTAGE, getSecondPlayer());
        return canBeApplied;
    }

    @Override
    public ResultType getResult() {
        return null;
    }


}
