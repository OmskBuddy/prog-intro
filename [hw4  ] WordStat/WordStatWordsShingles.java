import java.util.HashMap;
import java.util.TreeMap;
import java.util.SortedMap;
import java.lang.reflect.Field;
import java.lang.StringBuilder;
import java.io.Writer;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WordStatWordsShingles {
    public static void upd(SortedMap<String, Integer> counter, String key) {
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

        int dashUnicodeCategory = Character.DASH_PUNCTUATION;

        SortedMap<String, Integer> counter = new TreeMap<String, Integer>();

        try {
            Reader reader = new InputStreamReader(new FileInputStream(inputFile), "UTF8");
            try {
                int read = reader.read();
                StringBuilder curStr = new StringBuilder("");

                while (read >= 0) {
                    char cur = (char)read;
                    if (Character.isLetter(cur) || cur == '\'' || 
                            Character.getType(cur) == dashUnicodeCategory) {
                        curStr.append(cur);
                    } else if (curStr.length() > 0) {
                        if (curStr.length() < 3) {
                            upd(counter, curStr.toString().toLowerCase());
                        }
                        for (int i = 0; i < curStr.length() - 2; i++) { 
                            upd(counter, curStr.substring(i, i + 3).toLowerCase());
                        }
                        curStr.delete(0, curStr.length());
                    }

                    read = reader.read();
                }

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "UTF8"));
                try {
                    for (String i : counter.keySet()) {
                        writer.write(i + " " + counter.get(i));
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }
            } finally {
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file does not exist: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Input file reading error: " + e.getMessage());
        }
    }
}