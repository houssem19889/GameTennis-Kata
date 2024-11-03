package game.Impl;

import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * The type Tennis match.
 */
public class TennisMatch {
    private static final int SET_LIMIT = 3;
    private static final Scanner scanner = new Scanner(System.in);

    private final Player firstPlayer;
    private final Player secondPlayer;
    private final GameSet[] sets;
    private boolean lastSet;
    private boolean gameOver;
    private Player winner;

    /**
     * Instantiates a new Tennis match.
     *
     * @param firstPlayer  the first player
     * @param secondPlayer the second player
     * @param sets         the sets
     */
    public TennisMatch(Player firstPlayer, Player secondPlayer, GameSet[] sets) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);
        this.sets = Objects.requireNonNull(sets);
    }

    /**
     * Tennis match prompt tennis match.
     *
     * @return the tennis match
     */
    public static TennisMatch tennisMatchPrompt() {
        System.out.print("Player 1 name :");
        Player firstPlayer = new Player(scanner.nextLine());
        System.out.print("Player 2 name :");
        Player secondPlayer = new Player(scanner.nextLine());
        GameSet[] sets = new GameSet[SET_LIMIT];
        sets[0] = GameSet.between(firstPlayer, secondPlayer);
        sets[1] = GameSet.between(firstPlayer, secondPlayer);
        return new TennisMatch(firstPlayer, secondPlayer, sets);
    }


    /**
     * Is last set boolean.
     *
     * @return the boolean
     */
    public boolean isLastSet() {
        return lastSet;
    }

    /**
     * Run.
     */
    public void run() {
        try {
            Game game = Game.between(firstPlayer, secondPlayer);

            while (!gameOver) {
                System.out.print("COMMAND> ");
                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        if (applyIncrement(game::incrementFirstPlayer, currentSet()::incrementFirstPlayer, firstPlayer)) {
                            game.reset();
                        }
                        break;
                    case "2":
                        if (applyIncrement(game::incrementSecondPlayer, currentSet()::incrementSecondPlayer, secondPlayer)) {
                            game.reset();
                        }
                        break;
                    case "s":
                        displayScores(game);
                        System.out.println("------------------------------");
                        break;
                    default:
                        displayUsage();
                        break;
                }

                if (winner != null) {
                    System.out.println("MATCH WON by " + winner.getNamePlayer());
                    break;
                }
            }
        } finally {
            scanner.close();
        }
    }

    private void displayScores(Game game) {
        if (isLastSet()) {
            System.out.println(String.format("%s:\n SCORE : %d \t SETS : %d | %d",
                    firstPlayer.getNamePlayer(), game.firstPlayerScore(), sets[0].firstPlayerScore(), currentSet().firstPlayerScore()));
            System.out.println(String.format("%s:\n SCORE : %d \t SETS : %d | %d",
                    secondPlayer.getNamePlayer(), game.secondPlayerScore(), sets[0].secondPlayerScore(), currentSet().secondPlayerScore()));
        } else {
            System.out.println(String.format("%s:\n SCORE : %d \t SET : %d",
                    firstPlayer.getNamePlayer(), game.firstPlayerScore(), sets[0].firstPlayerScore(), currentSet().firstPlayerScore()));
            System.out.println(String.format("%s:\n SCORE : %d \t SET : %d",
                    secondPlayer.getNamePlayer(), game.secondPlayerScore(), sets[0].secondPlayerScore(), currentSet().secondPlayerScore()));
        }
    }

    private void displayUsage() {
        System.out.println("Available commands:");
        System.out.println("1 - Increment the first player's score");
        System.out.println("2 - Increment the second player's score");
        System.out.println("s - Display current scores");
        System.out.println("Other - Display this help message");
    }


    /**
     * @param gameAction
     * @param setAction
     * @param ifGameWon
     * @return true if the game is won, false otherwise
     */
    private boolean applyIncrement(Supplier<Boolean> gameAction, Supplier<Boolean> setAction, Player ifGameWon) {
        Boolean result = gameAction.get();
        // if game won
        if (!result) {
            boolean setWon = !setAction.get();
            if (setWon && isLastSet()) {
                gameOver = true;
                winner = ifGameWon;
            }
        }
        return !result;
    }

    private GameSet currentSet() {
        if (sets[0].isFinished()) {
            lastSet = true;
            return sets[1];
        } else return sets[0];
    }
}
