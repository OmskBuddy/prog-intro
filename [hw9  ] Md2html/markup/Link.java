package md2html.markup;

import java.util.ArrayList;
import java.util.List;

public class Link implements Editable {

    ArrayList<Editable> words;
    String link;
    private String open = "<a href='";
    private String close = "</a>";

    public Link(String link) {
        this.words = new ArrayList<>();
        this.link = link;
        this.open += link + "'>";
    }

    @Override
    public void toHtml(StringBuilder x) {
        x.append(open);
        for (Editable a : words) {
            a.toHtml(x);
        }
        x.append(close);
    }

    @Override
    public void add(Editable x) {
        words.add(x);
    }

    @Override
    public void print() {

    }
}
