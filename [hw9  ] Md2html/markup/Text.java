package md2html.markup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

public class Text implements Editable {

    StringBuilder text;

    public Text() {
        this.text = new StringBuilder();
    }

    public Text(String a) {
        text = new StringBuilder(a);
    }

    @Override
    public void toHtml(StringBuilder x) {
        x.append(text);
    }

    @Override
    public void add(Editable x) {
        StringBuilder added = new StringBuilder();
        x.toHtml(added);
        //System.err.println("added Text: " + added);
        text.append(added);
    }

    @Override
    public void print() {
        System.err.println(text);
    }
}
