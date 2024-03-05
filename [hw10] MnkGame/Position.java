package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public interface Position {
    boolean isValid(Move move);

    Cell getCell(int r, int c);
}
