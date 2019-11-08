package game;

import game.Impl.Player;
import game.utilEnum.ResultType;

/**
 * Created by houssem89 on 08/11/2019.
 */
public interface IRuleResult {
    /**
     * Gets score.
     *
     * @return the score
     */
    Player getScore();

    /**
     * Gets type.
     *
     * @return the type
     */
    ResultType getType();


}
