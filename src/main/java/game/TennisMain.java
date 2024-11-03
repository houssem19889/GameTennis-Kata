package game;

import game.Impl.TennisMatch;

/**
 * The type Tennis main.
 */
public class TennisMain {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        TennisMatch match = TennisMatch.tennisMatchPrompt();
        match.run();
    }
}
