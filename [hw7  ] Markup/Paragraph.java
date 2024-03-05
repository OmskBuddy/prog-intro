package markup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Paragraph extends Item implements Mark {

    ArrayList<Editable> paragraph;

    public Paragraph(List<Editable> paragraph) {
        this.paragraph = new ArrayList<>(paragraph);
    }

    @Override
    public void toMarkdown(StringBuilder x) {
        for (Mark text : paragraph) {
            text.toMarkdown(x);
        }
    }

    @Override
    public void toTex(StringBuilder x) {
        for (Tex text : paragraph) {
            text.toTex(x);
        }
    }
}
