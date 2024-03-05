package game;

import java.util.Arrays;

public class MnkBoardWithBarriers extends MnkBoard {

    public MnkBoardWithBarriers(int k, int numOfPlayers) {
        super(11, 11, k, numOfPlayers);
        for (int i = 0; i < n; i++) {
            this.cells[i][i] = Cell.B;
            this.cells[i][this.n - i - 1] = Cell.B;
        }
        this.numOfEmpty -= 2 * n - 1;
    }

    @Override
    public void reset() {
        numOfEmpty = n * m - 2 * n + 1;
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        for (int i = 0; i < numOfPlayers; i++) {
            active[i] = true;
        }
        for (int i = 0; i < n; i++) {
            cells[i][i] = Cell.B;
            cells[i][n - i - 1] = Cell.B;
        }
        turn = Cell.T1;
    }

}
