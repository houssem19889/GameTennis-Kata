package game.Impl.state;

import game.Impl.Player;
import game.Impl.RuleResultImpl;
import game.utilEnum.ResultType;


/**
 * The type Advantage.
 */
public class Advantage extends GameRule {
    /**
     * Instantiates a new Advantage.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     */
    public Advantage(Player firstPlayer, Player secondPlayer) {
        super(firstPlayer, secondPlayer);
    }

    @Override
    public boolean canBeApplied() {
        boolean hasAdvantage = playerHasAdvantage(getFirstPlayer()) || playerHasAdvantage(getSecondPlayer());

        if (hasAdvantage) {
            this.ruleResult = getFirstPlayer().getScore() == GameRule.MAX_SCORE
                    ? new RuleResultImpl(ResultType.ADVANTAGE, getFirstPlayer())
                    : new RuleResultImpl(ResultType.ADVANTAGE, getSecondPlayer());
        }
        return hasAdvantage;
    }

    private boolean playerHasAdvantage(Player player) {
        return player.getScore() == GameRule.MAX_SCORE;
    }
    @Override
    public ResultType getResult() {
        return null;
    }


}
