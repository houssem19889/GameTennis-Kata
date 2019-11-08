package game;
import game.Exception.EnterScoreException;
import junit.framework.Assert;
import org.junit.Test;
import  game.Impl.SetScore;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
/**
 * Created by houssem89 on 08/11/2019.
 */
public class SetScoreTest {


    @Test
    public void initialization() {
        assertEquals(0, new SetScore() .getCurrentScore());
    }

    @Test
    public void increment() {
        SetScore score = new SetScore();
        assertTrue(score.incrementScore());
        assertEquals(1, score.getCurrentScore());
    }

    @Test
    public void maxScore() {
        SetScore score = new SetScore();
        assertTrue(score.incrementScore());
        assertEquals(1, score.getCurrentScore());
        assertTrue(score.incrementScore());
        assertEquals(2, score.getCurrentScore());
        assertTrue(score.incrementScore());
        assertEquals(3, score.getCurrentScore());
        assertTrue(score.incrementScore());
        assertEquals(4, score.getCurrentScore());
        assertTrue(score.incrementScore());
        assertEquals(5, score.getCurrentScore());
        assertTrue(score.incrementScore());
        assertEquals(6, score.getCurrentScore());
        assertTrue(score.incrementScore());
        assertEquals(7, score.getCurrentScore());
        assertFalse(score.incrementScore());
        assertEquals(7, score.getCurrentScore());
    }


}
