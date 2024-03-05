package game;

import java.util.Arrays;
import java.util.Map;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 * @updatedBy Nikita Klushkin
 */
public class MnkBoard implements Board, Position {

    protected final Pair[] trans = {new Pair(0, 1), new Pair(1, 0),
            new Pair(1, 1), new Pair(1, -1)};

    protected static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.T1, 'X',
            Cell.T2, 'O',
            Cell.T3, '-',
            Cell.T4, '|',
            Cell.E, '.',
            Cell.B, '#'
    );

    protected Cell[][] cells;
    protected Cell turn;
    protected final int m;
    protected final int n;
    protected final int k;
    protected int numOfEmpty;
    protected String hat = "";
    protected boolean[] active;
    protected int numOfPlayers;

    public MnkBoard(int m, int n, int k, int numOfPlayers) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.numOfEmpty = m * n;
        this.cells = new Cell[m][n];

        this.numOfPlayers = numOfPlayers;
        this.active = new boolean[numOfPlayers];

        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        for (int i = 0; i < n; i++) {
            this.hat += i + " ".repeat(Integer.toString(m - 1).length() -
                    Integer.toString(i).length() + 1);
        }
        for (int i = 0; i < numOfPlayers; i++) {
            active[i] = true;
        }
        turn = Cell.T1;
    }
    public MnkBoard(Triple mnk, int numOfPlayers) {
        this(mnk.getFirst(), mnk.getSecond(), mnk.getThird(), numOfPlayers);
    }
    public MnkBoard(int m, int n, int k) {
        this(m, n, k, 2);
    }
    public MnkBoard() {
        this(3, 3, 3);
    }

    @Override
    public Position getPosition() {
        return new MnkPosition(this);
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            changeTurn();
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        numOfEmpty--;

        int row = move.getRow();
        int col = move.getColumn();

        for (Pair x : trans) {
            int newRow = row + x.getFirst();
            int newCol = col + x.getSecond();
            int cnt = 1;

            while (0 <= newRow && newRow < m && 0 <= newCol && newCol < n &&
                    cells[row][col] == cells[newRow][newCol]) {
                newRow += x.getFirst();
                newCol += x.getSecond();
                cnt++;
            }
            newRow = row - x.getFirst();
            newCol = col - x.getSecond();
            while (0 <= newRow && newRow < m && 0 <= newCol && newCol < n &&
                    cells[row][col] == cells[newRow][newCol]) {
                newRow -= x.getFirst();
                newCol -= x.getSecond();
                cnt++;
            }

            if (cnt >= k) {
                changeTurn();
                return Result.WIN;
            }
        }

        if (numOfEmpty == 0) {
            changeTurn();
            return Result.DRAW;
        }

        changeTurn();
        return Result.UNKNOWN;
    }

    private void changeTurn() {
        int i = (turn.ordinal() + 1) % numOfPlayers;
        while (!active[i]) {
            i = (i + 1) % numOfPlayers;
        }
        turn = Cell.values()[i];
    }

    @Override
    public void disablePlayer(int num) {
        active[num] = false;
    }

    @Override
    public void reset() {
        numOfEmpty = n * m;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        for (int i = 0; i < numOfPlayers; i++) {
            active[i] = true;
        }
        turn = Cell.T1;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getColumn() && move.getColumn() < n
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final int maxNum = Integer.toString(m - 1).length();
        final StringBuilder sb = new StringBuilder(
                " ".repeat(2 * (maxNum + 1) - (maxNum == 1 ? 1 : 0)) + hat);
        for (int r = 0; r < m; r++) {
            sb.append(System.lineSeparator());
            sb.append(r).append(" ".repeat(2 * maxNum - Integer.toString(r).length()));
            for (int c = 0; c < n; c++) {
                sb.append(" ".repeat(maxNum) + SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }
}
