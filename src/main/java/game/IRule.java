package game;

import game.utilEnum.ResultType;


/**
 * The interface Rule.
 */
public interface IRule {
    /**
     * Can be applied.
     *
     * @return the boolean
     */
    boolean canBeApplied();

    /**
     * Gets result.
     *
     * @return the result
     */
    ResultType getResult();
}
