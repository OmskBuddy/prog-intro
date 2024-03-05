package markup;

import java.util.ArrayList;
import java.util.List;

public class TexList extends Item {

    List<ListItem> items;
    protected String beginEnv = "";
    protected String endEnv = "";


    public TexList(List<ListItem> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public void toTex(StringBuilder x) {
        x.append(beginEnv);
        for (ListItem item : items) {
            item.toTex(x);
        }
        x.append(endEnv);
    }
}
