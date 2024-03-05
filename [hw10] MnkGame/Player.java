package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public interface Player {
    Move move(Position position, Cell cell);

    boolean isLose();

    void disablePlayer();
}
