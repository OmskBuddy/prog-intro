import java.io.*;
import java.util.LinkedHashMap;

public class Wspp {
    public static void upd(LinkedHashMap<String, IntList> a, String key, int pos) {
        IntList value = a.get(key);
        if (value == null) {
            a.put(key, new IntList(new int[]{pos}));
        } else {
            value.add(pos);
            a.replace(key, value);
        }
    }

    public static void main(String[] args) {
        final String encoding = "UTF-8";

        String testIn = args[0];
        String testOut = args[1];

        LinkedHashMap<String, IntList> positions = new LinkedHashMap<>();  

        try {
            Scanner in = new Scanner(new InputStreamReader
            (
                new FileInputStream(testIn), encoding), 
                true
            );
            try {
                int pos = 0;
                while (in.hasNext()) {
                    String word = in.next().toLowerCase();
                    //System.err.println(word);
                    upd(positions, word, ++pos);
                }

                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(testOut),
                    encoding
                ));
                try {
                    for (String s : positions.keySet()) {
                        IntList p = positions.get(s);
                        out.write(s + " " + p.length);
                        for (int i = 0; i < p.length; i++) {
                            out.write(" " + p.get(i));
                        }
                        out.newLine();
                    }
                } finally {
                    out.close();
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file doesn't exists: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        }
    }
}