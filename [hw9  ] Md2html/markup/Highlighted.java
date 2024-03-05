package md2html.markup;

import java.util.ArrayList;

public class Highlighted implements Editable {
    ArrayList<Editable> words;

    String open = "";
    String close = "";

    public Highlighted() {
        this.words = new ArrayList<>();
    }

    @Override
    public void add(Editable x) {
        words.add(x);
    }

    @Override
    public void print() {

    }

    @Override
    public void toHtml(StringBuilder x) {
        x.append(open);
        for (Editable a : words) {
            a.toHtml(x);
        }
        x.append(close);
    }
}
