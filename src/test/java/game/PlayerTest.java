package game;

import game.Impl.Player;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


/**
 * The type Player test.
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
