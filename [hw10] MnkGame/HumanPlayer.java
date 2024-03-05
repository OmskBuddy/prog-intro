package game;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in;
    private boolean active = true;

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        out.println("Position");
        out.println(position);
        out.println(cell + "'s move");
        out.println("Enter row and column:");

        final Pair steps = Pair.enterPair(out, in);
        return new Move(steps.getFirst(), steps.getSecond(), cell);
    }

    @Override
    public boolean isLose() {
        return !active;
    }

    @Override
    public void disablePlayer() {
        active = false;
    }
}
