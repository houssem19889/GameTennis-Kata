package game.utilEnum;

import java.util.Arrays;
import java.util.function.BiPredicate;

/**
 * Created by houssem89 on 08/11/2019.
 */
public enum ResultType {
    DEUCE((s1, s2) -> s1 == s2, "Deuce"),
    ADVANTAGE_first_PLAYER((s1, s2) -> s1 == s2 + 1, "Advantage first Player"),
    ADVANTAGE_Second_PLAYER((s1, s2) -> s2 == s1 + 1, "Advantage second Player"),
    first_PLAYER_WINS((s1, s2) -> s1 > s2 + 1, "First Player wins"),
    Second_PLAYER_WINS((s1, s2) -> s2 > s1 + 1, "Second Player wins"),
    ADVANTAGE,
    FINISHED ;


    private BiPredicate<Integer, Integer> check;
    public  String label;
    ResultType(BiPredicate<Integer, Integer> check,String label) {
        this.check = check;
        this.label = label;
    }

    ResultType() {

    }

    public static ResultType of(int player1Score, int player2Score) {
        return Arrays.stream(values()).filter(score -> score.check(player1Score, player2Score)).findAny().get();
    }
    private boolean check(int player1Score, int player2Score) {
        return this.check.test(player1Score, player2Score);
    }
}
