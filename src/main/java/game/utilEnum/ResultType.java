package game.utilEnum;

import java.util.Arrays;
import java.util.function.BiPredicate;

/**
 * Created by houssem89 on 08/11/2019.
 */
public enum ResultType {
    /**
     * The DEUCE.
     */
    DEUCE((s1, s2) -> s1 == s2, "Deuce"),

    /**
     * The first_PLAYER_WINS.
     */
    first_PLAYER_WINS((s1, s2) -> s1 > s2 + 1, "First Player wins"),
    /**
     * The Second_PLAYER_WINS.
     */
    Second_PLAYER_WINS((s1, s2) -> s2 > s1 + 1, "Second Player wins"),
    /**
     * The ADVANTAGE.
     */
    ADVANTAGE,
    /**
     * The FINISHED.
     */
    FINISHED;


    /**
     * The Label.
     */
    public String label;
    private BiPredicate<Integer, Integer> check;

    /**
     * Instantiates a new Result type.
     *
     * @param check the check
     * @param label the label
     */
    ResultType(BiPredicate<Integer, Integer> check, String label) {
        this.check = check;
        this.label = label;
    }

    /**
     * Instantiates a new Result type.
     */
    ResultType() {

    }

    /**
     * Of result type.
     *
     * @param player1Score the player 1 score
     * @param player2Score the player 2 score
     * @return the result type
     */
    public static ResultType of(int player1Score, int player2Score) {
        return Arrays.stream(values()).filter(score -> score.check(player1Score, player2Score)).findAny().get();
    }

    private boolean check(int player1Score, int player2Score) {
        return this.check.test(player1Score, player2Score);
    }
}
