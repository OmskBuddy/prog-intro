package md2html.markup;

import java.util.ArrayList;

public class Paragraph implements Editable {
    private final ArrayList<Editable> paragraph;
    final String open = "<p>";
    final String close = "</p>";

    public Paragraph() {
        paragraph = new ArrayList<>();
    }

    public ArrayList<Editable> getList() {
        return new ArrayList<>(paragraph);
    }

    @Override
    public void toHtml(StringBuilder x) {
        x.append(open);
        for (Editable a : paragraph) {
            a.toHtml(x);
        }
        x.append(close);
    }

    @Override
    public void add(Editable x) {
        paragraph.add(x);
    }

    @Override
    public void print() {

    }
}
