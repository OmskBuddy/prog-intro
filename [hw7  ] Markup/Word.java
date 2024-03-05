package markup;

import java.util.ArrayList;
import java.util.List;

public class Word implements Editable {

    List<Editable> paragraph;
    String markBoard = "";
    String texLeftBoard = "";
    String texRightBoard = "";

    Word(List<Editable> paragraph) {
        this.paragraph = new ArrayList<>(paragraph);
    }

    @Override
    public void toMarkdown(StringBuilder x) {
        x.append(markBoard);
        for (Mark text : paragraph) {
            text.toMarkdown(x);
        }
        x.append(markBoard);
    }

    @Override
    public void toTex(StringBuilder x) {
        x.append(texLeftBoard);
        for (Tex text : paragraph) {
            text.toTex(x);
        }
        x.append(texRightBoard);
    }
}
