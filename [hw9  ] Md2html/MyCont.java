package md2html;

public class MyCont implements Comparable<MyCont> {
    int n;
    String type;
    String data;

    public MyCont(int n, String type) {
        this.n = n;
        this.type = type;
        this.data = "";
    }

    public MyCont(int n, String type, String data) {
        this.n = n;
        this.type = type;
        this.data = data;
    }

    @Override
    public int compareTo(MyCont o) {
        if (this.n == o.n) {
            return this.type.length() - o.type.length();
        }
        return this.n - o.n;
    }
}
