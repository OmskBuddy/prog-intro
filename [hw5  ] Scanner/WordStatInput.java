import java.util.LinkedHashMap;
import java.lang.reflect.Field;
import java.lang.StringBuilder;
import java.io.*;

public class WordStatInput {
    public static void upd(LinkedHashMap<String, Integer> counter, String key) {
        Integer curValue = counter.get(key);
        if (curValue != null) {
            counter.replace(key, curValue + 1);
        } else {
            counter.put(key, 1);
        }
    }
    public static void main(String[] args) {
        String inputFile = args[0];
        String outputFile = args[1];

        LinkedHashMap<String, Integer> counter = new LinkedHashMap<String, Integer>();
        try {
            Scanner reader = new Scanner(new InputStreamReader(new FileInputStream(inputFile), "UTF8"), true);
            try {
                while (reader.hasNext()) {
                    String cur = reader.next().toLowerCase();
                    System.err.print(cur + " ");
                    upd(counter, cur);
                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "UTF8"));

                try {
                    for (String i : counter.keySet()) {
                        writer.write(i + " " + counter.get(i));
                        System.err.println(i + " " + counter.get(i));
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Input file does not exist: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Input file reading error: " + e.getMessage());
        }
    }
}