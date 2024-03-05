import java.util.ArrayList;
import java.util.List;

public class Word implements Comparable<Word> {
    String word;
    int first;
    int count;
    IntList positions;

    Word(String word, int first) {
        this.word = word;
        this.first = first;
        this.count = 1;
        positions = new IntList();
    }

    public void upd() {
        this.count++;
    }

    public void upd(int pos) {
        this.positions.add(pos);
    }

    @Override
    public int compareTo(Word that) {
        if (this.count == that.count) {
            return this.first - that.first;
        }
        return this.count - that.count;
    }
}
