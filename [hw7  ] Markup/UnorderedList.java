package markup;

import java.util.ArrayList;
import java.util.List;

public class UnorderedList extends TexList {

    public UnorderedList(List<ListItem> items) {
        super(items);
        this.beginEnv = "\\begin{itemize}";
        this.endEnv = "\\end{itemize}";
    }

}
