package game;

import java.io.PrintStream;
import java.util.*;

public class Menu {

    private final PrintStream out;
    private final Scanner in;

    public Menu(PrintStream out, Scanner in) {
        this.out = out;
        this.in = in;
    }

    public Menu() {
        this(System.out, new Scanner(System.in));
    }

    public void start() {
        out.println("Hello, friend. Please, choose the format of game:");

        int format = getFormat();
        int numOfPlayers = format == 1 ? 2 : getNumOfPlayers(format);
        int barriers = format <= 1 ? 1 : getBarrier();
        Triple mnk = getMNK(barriers);

        final Board board = barriers == 1 ? new MnkBoard(mnk, numOfPlayers) :
                new MnkBoardWithBarriers(mnk.getThird(), numOfPlayers);

        if (format != 2) {
            final Player[] players = new Player[numOfPlayers];
            for (int i = 0; i < numOfPlayers; i++) {
                players[i] = new HumanPlayer();
            }
            final Game game = new Game(false, players);

            int result = game.play(board);
            if (result == 0) {
                out.println("DRAW");
            } else {
                out.println("Player " + result + " won!");
            }
        } else {
            tournament(numOfPlayers, board);
        }
    }

    private void tournament(int numOfPlayers, Board board) {
        List<Pair> listOfGames = new ArrayList<>();
        int[] scores = new int[numOfPlayers];

        for (int i = 0; i < numOfPlayers; i++) {
            scores[i] = 0;
            for (int j = 0; j < numOfPlayers; j++) {
                if (i == j) {
                    continue;
                }
                listOfGames.add(new Pair(i, j));
            }
        }

        Collections.shuffle(listOfGames);
        for (Pair x : listOfGames) {
            board.reset();

            int result;
            HumanPlayer[] currentPlayers = new HumanPlayer[]{
                    new HumanPlayer(), new HumanPlayer()
            };

            final Game game = new Game(false, currentPlayers);

            out.println("Player " + (x.getFirst() + 1) + " is X");
            out.println("Player " + (x.getSecond() + 1) + " is O");

            result = game.play(board);

            if (result == 0) {
                scores[x.getFirst()]++;
                scores[x.getSecond()]++;
                out.println("DRAW");
            } else if (result == 1) {
                scores[x.getFirst()] += 3;
                out.println("Player " + (x.getFirst() + 1) + " won!");
            } else if (result == 2) {
                scores[x.getSecond()] += 3;
                out.println("Player " + (x.getSecond() + 1) + " won!");
            }
        }

        out.println("Result of tournament:");
        for (int i = 0; i < numOfPlayers; i++) {
            out.println(i + 1 + ": " + scores[i]);
        }
    }

    private int getFormat() {
        int result;
        out.println("[1] Classic MNK    [2] Tournament    [3] Multiplayer MNK");
        while (true) {
            out.print("format: ");
            if (in.hasNextInt()) {
                result = in.nextInt();

                if (1 <= result && result <= 3) {
                    break;
                }
                out.println("Wrong answer! Try again.");
            } else {
                in.next();
                out.println("Incorrect format of input. Try again.");
            }
        }
        return result;
    }

    private int getNumOfPlayers(int format) {
        int result;
        while (true) {
            out.print("number of player: ");
            if (in.hasNextInt()) {
                result = in.nextInt();

                if (format == 2) {
                    if (2 <= result && result <= 10000) {
                        break;
                    } else {
                        out.println("A bit many(few) player. Everything has limit.");
                    }
                } else if (format == 3) {
                    if (2 <= result && result <= 4) {
                        break;
                    } else {
                        out.println("Wrong answer! Try again.");
                    }
                }
            } else {
                in.next();
                out.println("Incorrect format of input. Try again.");
            }
        }
        return result;
    }

    private int getBarrier() {
        int result;
        out.println("[1] Without barriers   [2] With barriers");
        while (true) {
            out.print("presence of barriers: ");
            if (in.hasNextInt()) {
                result = in.nextInt();

                if (1 <= result && result <= 2) {
                    break;
                }
                out.println("Wrong answer! Try again.");
            } else {
                in.next();
                out.println("Incorrect format of input. Try again.");
            }
        }
        return result;
    }

    private int getNum(String s) {
        int result;
        while (true) {
            out.println(s);
            if (in.hasNextInt()) {
                result = in.nextInt();

                if (2 < result && result <= 1000) {
                    break;
                }
                out.println("A bit many(few). Everything has limit.");
            } else {
                in.next();
                out.println("Incorrect format of input. Try again.");
            }
        }
        return result;
    }

    private int getK(int m, int n) {
        int k;
        while (true) {
            k = getNum("K: ");
            if (k <= Math.min(m, n)) {
                break;
            }
            out.println("K can't be such. Try again.");
        }
        return k;
    }

    private Triple getMNK(int barriers) {
        int m;
        int n;
        int k;
        Triple result;

        if (barriers == 1) {
            m = getNum("M: ");
            n = getNum("N: ");
            k = getK(m, n);
        } else {
            m = 11;
            n = 11;
            k = getK(11, 11);
        }
        result = new Triple(m, n, k);
        return result;
    }

}
