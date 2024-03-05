package md2html.markup;

public interface Editable {
    void toHtml(StringBuilder x);

    void add(Editable x);

    void print();
}
