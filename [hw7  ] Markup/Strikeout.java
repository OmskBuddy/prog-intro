package markup;

import java.util.List;

public class Strikeout extends Word {

    public Strikeout(List<Editable> paragraph) {
        super(paragraph);
        this.markBoard = "~";
        this.texLeftBoard = "\\textst{";
        this.texRightBoard = "}";
    }
}
