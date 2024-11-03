package game;

import game.Exception.EnterScoreException;
import game.Impl.Score;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


/**
 * The type Score test.
 */
public class ScoreTest {

    /**
     * Play with score.
     *
     * @throws EnterScoreException the enter score exception
     */
    @Test
    public void playWithScore() throws EnterScoreException {
        IScore s = new Score();
        assertEquals(0, s.getCurrentScore());
        s = new Score(15);
        assertEquals(15, s.getCurrentScore());
        s = new Score(30);
        assertEquals(30, s.getCurrentScore());
    }

    /**
     * Increment.
     *
     * @throws EnterScoreException the enter score exception
     */
    @Test
    public void increment() throws EnterScoreException {
        IScore s = new Score();
        assertEquals(0, s.getCurrentScore());

        boolean state = s.incrementScore();
        assertTrue(state);
        assertEquals(15, s.getCurrentScore()); //0 => 15

        state = s.incrementScore();
        assertTrue(state);
        assertEquals(30, s.getCurrentScore()); // 15 => 30


        state = s.incrementScore();
        assertTrue(state);
        assertEquals(40, s.getCurrentScore()); // 30 => 40


        state = s.incrementScore();
        assertFalse(state);
        assertEquals(40, s.getCurrentScore()); // 40 max score
    }

    /**
     * Reset.
     *
     * @throws EnterScoreException the enter score exception
     */
    @Test
    public void reset() throws EnterScoreException {
        IScore s = new Score();

        boolean state = s.incrementScore();
        assertTrue(state);
        assertEquals(15, s.getCurrentScore()); // 0 => 15
        s.resetScore();
        assertEquals(0, s.getCurrentScore());
    }
}
