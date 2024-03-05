package markup;

import java.util.ArrayList;
import java.util.List;

public class Emphasis extends Word {

    public Emphasis(List<Editable> paragraph) {
        super(paragraph);
        this.markBoard = "*";
        this.texLeftBoard = "\\emph{";
        this.texRightBoard = "}";
    }
}
