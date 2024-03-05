package markup;

import java.util.ArrayList;
import java.util.List;

public class OrderedList extends TexList {

    public OrderedList(List<ListItem> items) {
        super(items);
        this.beginEnv = "\\begin{enumerate}";
        this.endEnv = "\\end{enumerate}";
    }

}
