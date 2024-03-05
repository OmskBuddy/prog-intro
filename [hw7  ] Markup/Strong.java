package markup;

import java.util.List;

public class Strong extends Word {

    public Strong(List<Editable> paragraph) {
        super(paragraph);
        this.markBoard = "__";
        this.texLeftBoard = "\\textbf{";
        this.texRightBoard = "}";
    }
}
