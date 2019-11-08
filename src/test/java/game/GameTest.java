package game;

import game.Impl.Game;
import game.Impl.Player;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by houssem89 on 07/11/2019.
 */
public class GameTest {


    @Test
    public void should_return_0_0_when_no_player_scored() {

        // Given
        Game game = Game.between("Rafael", "Federer");
        String expectedScore = "0 0";

        // When
        String score = game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }
    @Test
    public void should_return_15_0_when_first_player_scored() {
        //Given
        Game game = Game.between("Rafael", "Federer");
        game.incrementFirstPlayer();
        String expectedScore = "15 0";
        //when
        String score = game.score();
        //then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }
    @Test
    public void should_return_0_15_when_second_player_scored() {
        //Given
       Game game = Game.between("Rafael", "Federer");
        game.incrementSecondPlayer();
        String expectedScore = "0 15";
        //when
        String score = game.score();
        //then
        Assertions.assertThat(score).isEqualTo(expectedScore);
        assertEquals(15, game.getSecondPlayer().getScore());
    }

    @Test
    public void should_return_15_15_when_first_player_scores_and_the_score_was_0_15() {

        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("0 15");
        game.incrementFirstPlayer();
        String expectedScore = "15 15";

        // When
        String score = game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    public void should_return_15_15_when_player2_scores_and_the_score_was_15_0() {

        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("15 0");
        game.incrementSecondPlayer();
        String expectedScore = "15 15";
        // When
        String score = game.score();
        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    public void should_return_30_0_when_first_player_scores_and_the_score_was_15_0() {
        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("15 0");
        game.incrementFirstPlayer();
        String expectedScore = "30 0";
        // When
        String score = game.score();
        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }
    @Test
    public void should_return_0_30_when_second_player_scores_and_the_score_was_0_15() {
        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("0 15");
        game.incrementSecondPlayer();
        String expectedScore = "0 30";
        // When
        String score = game.score();
        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }
    @Test
    public void should_return_0_40_when_second_player_scores_and_the_score_was_0_30() {
        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("0 30");
        game.incrementSecondPlayer();
        String expectedScore = "Second Player wins";// 0  40

        // When
        String score = game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }


    @Test
    public void should_return_win_FirstPlayer() {
        Game game = Game.between("Rafael", "Federer");
        assertFalse(game.getWinner().isPresent());
        assertTrue(game.incrementFirstPlayer()); // 15 - 0
        assertTrue(game.incrementFirstPlayer()); // 30 - 0
        assertTrue(game.incrementFirstPlayer()); // 40 - 0

         assertFalse(game.incrementFirstPlayer());
         assertFalse(game.incrementFirstPlayer());

        Optional<IPlayer> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Rafael", winningPlayer.get().getNamePlayer());
    }

    @Test
    public void should_win_SecondPlayer() {
        Game game = Game.between("Rafael", "Federer");
        assertFalse(game.getWinner().isPresent());
        assertTrue(game.incrementSecondPlayer()); // 0 - 15
        assertTrue(game.incrementSecondPlayer()); // 0 - 30
        assertTrue(game.incrementSecondPlayer()); // 0 - 40
        assertFalse(game.incrementSecondPlayer());
        Optional<IPlayer> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Federer", winningPlayer.get().getNamePlayer());
    }
    @Test
    public void should_return_first_Player_Score(){
        Game game = Game.between("Rafael", "Federer");
        assertEquals(0, game.firstPlayerScore());
    }

    @Test
    public void should_return_second_Player_Score(){
        Game game = Game.between("Rafael", "Federer");
        assertEquals(0, game.secondPlayerScore());
    }

    @Test
    public void should_return_Deuce_when_first_player_scores_and_the_score_was_30_40() {
        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("30 40");
        game.incrementFirstPlayer();
        String expectedScore = "Deuce";

        // When
        String score = game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }

    @Test
    public void deuceToWinFirstPlayer() {
        //given
        Game game = Game.between("Rafael", "Federer");
        String expectedScore = "Deuce";
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 15 - 15
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 30 - 30
        assertTrue(game.incrementFirstPlayer());
        assertTrue(game.incrementSecondPlayer()); // 40 -40
        assertTrue(game.isDeuce());
        //when
        String score =game.score();
        //then
         Assertions.assertThat(score).isEqualTo(expectedScore);
        assertTrue(game.incrementFirstPlayer()); // player one has advantage

        assertTrue(game.getFirstPlayer().isHasAdvantage());
        assertFalse(game.getSecondPlayer().isHasAdvantage());

        assertTrue(game.incrementSecondPlayer()); // return to deuce
        assertTrue(game.isDeuce());

        assertTrue(game.incrementFirstPlayer()); // player one has advantage
        assertFalse(game.incrementFirstPlayer()); // player one has won the game


        Optional<IPlayer> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Rafael", winningPlayer.get().getNamePlayer());

        assertTrue(game.isFinished());
    }

    @Test
    public void should_the_game_isDeuce() {
        //given
        Game game = Game.between("Rafael", "Federer");
        String expectedScore = "Deuce";
        assertFalse(game.isDeuce()); // 0 - 0
        game.incrementFirstPlayer();
        assertFalse(game.isDeuce()); // 15 - 0
        game.incrementSecondPlayer();
        game.incrementSecondPlayer();
        assertFalse(game.isDeuce()); // 15 - 30

        game.incrementFirstPlayer();
        game.incrementFirstPlayer();
        game.incrementSecondPlayer();

        //when
        String score =game.score();
        //then
        assertTrue(game.isDeuce()); // 40 - 40
    }


    @Test
    public void winningSecondPlayer() {
        Game game = Game.between("Rafael", "Federer");
        String expectedScore = "Second Player wins";
        assertFalse(game.getWinner().isPresent());
        assertTrue(game.incrementSecondPlayer()); // 0 - 15
        assertTrue(game.incrementSecondPlayer()); // 0 - 30
        assertTrue(game.incrementSecondPlayer()); // 0 - 40
        //when
        String score =game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
        assertFalse(game.incrementSecondPlayer()); // game won
        Optional<IPlayer> winningPlayer = game.getWinner();
        assertTrue(winningPlayer.isPresent());
        assertEquals("Federer", winningPlayer.get().getNamePlayer());
    }
}
