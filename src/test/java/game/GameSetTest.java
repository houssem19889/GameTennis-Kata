package game;

import game.Impl.GameSet;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;


/**
 * The type Game set test.
 */
public class GameSetTest {
    /**
     * First player domination.
     */
    @Test
    public void firstPlayerDomination(){
    GameSet set = GameSet.between("Nadal", "Federer");
    assertEquals(0, set.firstPlayerScore());
    assertEquals(0, set.secondPlayerScore());

    assertTrue(set.incrementFirstPlayer());
    assertEquals(1, set.firstPlayerScore());

    assertTrue(set.incrementFirstPlayer());
    assertEquals(2, set.firstPlayerScore());

    assertTrue(set.incrementFirstPlayer());
    assertEquals(3, set.firstPlayerScore());

    assertTrue(set.incrementFirstPlayer());
    assertEquals(4, set.firstPlayerScore());

    assertTrue(set.incrementFirstPlayer());
    assertEquals(5, set.firstPlayerScore());

    assertFalse(set.incrementFirstPlayer());
    assertEquals(6, set.firstPlayerScore());
    assertFalse(set.incrementFirstPlayer()); // already won

    Optional<IPlayer> winner = set.getWinner();
    assertTrue(winner.isPresent());
    assertEquals("Nadal", winner.get().getNamePlayer());
}

    /**
     * Second player domination.
     */
    @Test
        public void secondPlayerDomination(){
            GameSet set = GameSet.between("Nadal", "Federer");
            assertEquals(0, set.secondPlayerScore());
            assertEquals(0, set.firstPlayerScore());

            assertTrue(set.incrementSecondPlayer());
            assertEquals(1, set.secondPlayerScore());

            assertTrue(set.incrementSecondPlayer());
            assertEquals(2, set.secondPlayerScore());

            assertTrue(set.incrementSecondPlayer());
            assertEquals(3, set.secondPlayerScore());

            assertTrue(set.incrementSecondPlayer());
            assertEquals(4, set.secondPlayerScore());

            assertTrue(set.incrementSecondPlayer());
            assertEquals(5, set.secondPlayerScore());

            assertFalse(set.incrementSecondPlayer());
            assertEquals(6, set.secondPlayerScore());

            assertFalse(set.incrementSecondPlayer());
            Optional<IPlayer> winner = set.getWinner();
            assertTrue(winner.isPresent());
            assertEquals("Federer", winner.get().getNamePlayer());
        }

    /**
     * First player wins by tie break.
     */
    @Test
        public void firstPlayerWinsByTieBreak(){
            GameSet set = GameSet.between("Nadal", "Federer");
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 1 -1
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 2 - 2
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 3 - 3
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 4 - 4
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 5 - 5
            assertTrue(set.incrementSecondPlayer());  // 5 - 6
            assertFalse(set.isFinished());
            assertTrue(set.incrementFirstPlayer()); // 6 - 6
            assertFalse(set.isFinished());
            assertTrue(set.incrementFirstPlayer()); // 7 - 6
            assertFalse(set.isFinished());
            assertTrue(set.incrementSecondPlayer());  // 7 - 7
            assertFalse(set.isFinished());

            assertTrue(set.incrementFirstPlayer()); // 8 - 7
            assertFalse(set.isFinished());

            assertFalse(set.incrementFirstPlayer()); // 9 - 7
            assertTrue(set.isFinished());
            Optional<IPlayer> winner = set.getWinner();
            assertEquals("Nadal", winner.get().getNamePlayer());
        }

    /**
     * Second player wins by tie break.
     */
    @Test
        public void secondPlayerWinsByTieBreak(){
            GameSet set = GameSet.between("Nadal", "Federer");
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 1 -1
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 2 - 2
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 3 - 3
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 4 - 4
            set.incrementFirstPlayer();
            set.incrementSecondPlayer(); // 5 - 5
            assertTrue(set.incrementFirstPlayer());  // 6 - 5
            assertFalse(set.isFinished());
            assertTrue(set.incrementSecondPlayer()); // 6 - 6
            assertFalse(set.isFinished());
            assertTrue(set.incrementSecondPlayer()); // 6 - 7
            assertFalse(set.isFinished());
            assertTrue(set.incrementFirstPlayer());  // 7 - 7
            assertFalse(set.isFinished());

            assertTrue(set.incrementSecondPlayer()); // 7 - 8
            assertFalse(set.isFinished());

            assertFalse(set.incrementSecondPlayer()); // 7 - 9
            assertTrue(set.isFinished());
            Optional<IPlayer> winner = set.getWinner();
            assertEquals("Federer", winner.get().getNamePlayer());
        }

    /**
     * Get winner.
     */
    @Test
        public void getWinner(){
            GameSet set = GameSet.between("Nadal", "Federer");

            assertFalse(set.getWinner().isPresent());
        }

}
