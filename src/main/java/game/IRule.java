package game;

import game.utilEnum.ResultType;

/**
 * Created by houssem89 on 08/11/2019.
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
