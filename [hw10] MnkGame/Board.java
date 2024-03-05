package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move);
    void disablePlayer(int num);
    void reset();
}
