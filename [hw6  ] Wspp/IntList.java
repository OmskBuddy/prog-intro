import java.util.Arrays;

public class IntList {
    private int[] array;
    public int length;

    IntList() {
        this.array = new int[0];
        this.length = 0;
    }

    IntList(int[] array) {
        this.array = array;
        this.length = array.length;
    }

    IntList(IntList that) {
        this.array = that.array;
        this.length = that.length;
    }

    public void add(int x) {
        if (length >= array.length) {
            int newLength = array.length * 2 + 1;
            array = Arrays.copyOf(array, newLength);
        }
        array[++length - 1] = x;
    }

    public int get(int i) {
        return array[i];
    }
}