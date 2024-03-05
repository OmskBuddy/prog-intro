package markup;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

public class Text implements Editable {

    StringBuilder text;

    Text(String text) {
        this.text = new StringBuilder(text);
    }

    @Override
    public void toMarkdown(StringBuilder x) {
        x.append(this.text);
    }

    @Override
    public void toTex(StringBuilder x) {
        x.append(this.text);
    }
}
