package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public class Game {
    private final boolean log;
    private final Player[] players;
    private int numOfActivePlayers;

    public Game(final boolean log, final Player[] players) {
        this.log = log;
        this.players = players;
        this.numOfActivePlayers = players.length;
    }

    public int play(Board board) {
        while (true) {
            for (int i = 0; i < players.length; i++) {
                if (players[i].isLose()) {
                    continue;
                }

                int result = numOfActivePlayers == 1 ? (i + 1) : move(board, players[i], i + 1);

                if (result != -1) {
                    return result;
                }
            }
        }
    }

    private int move(final Board board, final Player player, final int no) {
        final Move move = player.move(board.getPosition(), board.getCell());
        final Result result = board.makeMove(move);

        log("Player " + no + " move: " + move);
        log("Position:\n" + board);

        if (result == Result.WIN) {
            log("Player " + no + " won");
            return no;
        } else if (result == Result.DRAW) {
            log("Draw");
            return 0;
        } else if (result == Result.LOSE) {
            log("Player " + no + " lost");
            player.disablePlayer();
            board.disablePlayer(no - 1);
            numOfActivePlayers--;
        }
        return -1;
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }
}
