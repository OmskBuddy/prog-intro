import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class WsppCountLastL {
    public static void lastUpd(Map<String, Integer> last, String word, int linePos) {
        Integer value = last.get(word);
        if (value != null) {
            last.replace(word, Math.max(value, linePos));
        } else {
            last.put(word, linePos);
        }
    }
    public static void wordUpd(Map<String, Word> text, String word, int pos, boolean isLast) {
        Word value = text.get(word);
        if (value != null) {
            if (isLast) {
                value.upd(pos);
            } else {
                value.upd();
            }
            text.replace(word, value);
        } else {
            text.put(word, new Word(word, pos));
        }
    }
    public static void main(String[] args) {
        final String encoding = "UTF8";

        String testIn = args[0];
        String testOut = args[1];

        Map<String, Word> text = new HashMap<>();
        List<Word> list = new ArrayList<>();

        try {
            Scanner in = new Scanner (
                    new InputStreamReader(
                            new FileInputStream(testIn),
                            encoding
                    ),
                    true
            );
            try {
                int pos = 1;
                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    //System.err.println(line);
                    Scanner curIn = new Scanner(line, true);
                    Map<String, Integer> last = new HashMap<>();

                    int linePos = 1;
                    while (curIn.hasNext()) {
                        String word = curIn.next().toLowerCase();
                        lastUpd(last, word, linePos);
                        wordUpd(text, word, pos, false);

                        linePos++;
                        pos++;
                    }

                    for (String key : last.keySet()) {
                        wordUpd(text, key, last.get(key), true);
                    }
                }
            } finally {
                in.close();
            }

            for (String key : text.keySet()) {
                list.add(text.get(key));
            }

            Collections.sort(list);

            BufferedWriter out = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(testOut),
                            encoding
                    )
            );

            /*
            System.err.println();
            for (Word word : list) {
                System.err.print(word.word + " " + word.count + " ");
                for (int i = 0; i < word.positions.length; i++) {
                    System.err.print(word.positions.get(i) + " ");
                }
                System.err.println();
            }
            */

            try {
                for (Word word : list) {
                out.write(word.word + " " + word.count);
                for (int i = 0; i < word.positions.length; i++) {
                    out.write(" " + word.positions.get(i));
                }
                out.newLine();
            }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("FILE ERROR " + e.getMessage());
        } catch (IOException e) {
            System.err.println("IO ERROR " + e.getMessage());
        }
    }
}
