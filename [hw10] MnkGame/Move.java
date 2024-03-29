package game;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public final class  Move {
    private final int row;
    private final int column;
    private final Cell value;
    boolean valid;

    public Move(final int row, final int column, final Cell value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.valid = true;
    }

    public void invalid() {
        valid = false;
    }

    public boolean isValid() {
        return valid;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Cell getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "row=" + row + ", column=" + column + ", value=" + value;
    }
}
