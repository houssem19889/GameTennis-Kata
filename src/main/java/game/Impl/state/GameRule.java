package game.Impl.state;

import game.IRule;
import game.IRuleResult;
import game.Impl.Player;

/**
 * Created by houssem89 on 08/11/2019.
 */
public abstract class GameRule implements IRule {
    /**
     * The MAX _ sCORE.
     */
    static final int MAX_SCORE = 40;
    private final Player firstPlayer;
    private final Player secondPlayer;
    /**
     * The Rule result.
     */
    protected IRuleResult ruleResult;

    /**
     * Instantiates a new Game rule.
     *
     * @param firstPlayer the first player
     * @param secondPlayer the second player
     */
    public GameRule(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    /**
     * Gets first player.
     *
     * @return the first player
     */
    public Player getFirstPlayer() {
        return firstPlayer;
    }

    /**
     * Gets second player.
     *
     * @return the second player
     */
    public Player getSecondPlayer() {
        return secondPlayer;
    }

    /**
     * Gets rule result.
     *
     * @return the rule result
     */
    public IRuleResult getRuleResult() {
        return ruleResult;
    }
}
