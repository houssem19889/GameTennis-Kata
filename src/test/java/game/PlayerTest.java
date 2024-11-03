package game;

import game.Impl.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by houssem89 on 07/11/2019.
 */
public class PlayerTest {

    /**
     * Increment score.
     */
    @Test
    public void incrementScore() {
        IPlayer p = new Player("Tennis Match to Player");
        assertEquals(0, p.getScore());

        boolean state = p.incrementScore();
        assertTrue(state);
        assertEquals(15, p.getScore());
    }
}
