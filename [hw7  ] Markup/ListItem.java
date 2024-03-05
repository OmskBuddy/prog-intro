package markup;

import java.util.ArrayList;
import java.util.List;

public class ListItem implements Tex {

    List<Item> items;
    final String tag = "\\item ";

    public ListItem(List<Item> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public void toTex(StringBuilder x) {
        x.append(tag);
        for (Item item : items) {
            item.toTex(x);
        }
    }
}
