package md2html.markup;

import java.util.ArrayList;

public class Headline implements Editable {

    int typeOfHeadline;
    ArrayList<Editable> headline;
    String open;
    String close;

    public Headline(int typeOfHeadline) {
        this.typeOfHeadline = typeOfHeadline;
        this.headline = new ArrayList<>();
        this.open = String.format("<h%d>", typeOfHeadline);
        this.close = String.format("</h%d>", typeOfHeadline);
    }

    public Headline(int typeOfHeadline, Paragraph paragraph) {
        this.typeOfHeadline = typeOfHeadline;
        this.headline = paragraph.getList();
        this.open = String.format("<h%d>", typeOfHeadline);
        this.close = String.format("</h%d>", typeOfHeadline);
    }

    @Override
    public void toHtml(StringBuilder x) {
        x.append(open);
        for (Editable a : headline) {
            a.toHtml(x);
        }
        x.append(close);
    }

    @Override
    public void add(Editable x) {
        headline.add(x);
    }

    @Override
    public void print() {

    }
}
