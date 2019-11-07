package game;

import game.Impl.Game;
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
    public void shoud_return_0_0_when_no_player_scored() {

        // Given
        Game game = Game.between("Rafael", "Federer");
        String expectedScore = "0 0";

        // When
        String score = game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }
    @Test
    public void shoud_return_15_0_when_first_player_scored() {
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
    public void shoud_return_0_15_when_second_player_scored() {
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
    public void shoud_return_15_15_when_first_player_scores_and_the_score_was_0_15() {

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
    public void shoud_return_15_15_when_player2_scores_and_the_score_was_15_0() {

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
    public void shoud_return_30_0_when_first_player_scores_and_the_score_was_15_0() {
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
    public void shoud_return_0_30_when_second_player_scores_and_the_score_was_0_15() {
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
    public void shoud_return_0_40_when_second_player_scores_and_the_score_was_0_30() {
        // Given
        Game game = Game.between("Rafael", "Federer");
        game= game.gameScore("0 30");
        game.incrementSecondPlayer();
        String expectedScore = "0 40";

        // When
        String score = game.score();

        // Then
        Assertions.assertThat(score).isEqualTo(expectedScore);
    }


    @Test
    public void firstPlayerScore(){
        Game game = Game.between("Rafael", "Federer");
        assertEquals(0, game.firstPlayerScore());
    }

    @Test
    public void secondPlayerScore(){
        Game game = Game.between("Rafael", "Federer");
        assertEquals(0, game.secondPlayerScore());
    }
}
