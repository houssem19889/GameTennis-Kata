package game.Impl;

import game.IRuleResult;
import game.utilEnum.ResultType;


/**
 * The type Rule result.
 */
public class RuleResultImpl implements IRuleResult {
    private ResultType resultType;
    private Player player;

    /**
     * Instantiates a new Rule result impl.
     *
     * @param resultType the result type
     * @param player     the player
     */
    public RuleResultImpl(ResultType resultType, Player player) {
        this.resultType = resultType;
        this.player = player;
    }

    @Override
    public Player getScore() {
        return this.player = player;
    }

}
