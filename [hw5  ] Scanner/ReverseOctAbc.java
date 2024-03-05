import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class ReverseOctAbc {
    public static String parse(String a) {
        StringBuilder word = new StringBuilder(a);
        int radix = 10;

        for (int i = 0; i < word.length(); i++) {
            if ('a' <= word.charAt(i) && word.charAt(i) <= 'j') {
                word.setCharAt(
                    i, 
                    (char)('0' + word.charAt(i) - 'a')
                );
            }
        }

        if (word.charAt(word.length() - 1) == 'o' || word.charAt(word.length() - 1) == 'O') {
            radix = 8;
            word.deleteCharAt(word.length() - 1);
        }

        String total = Long.toString((int) Long.parseLong(word.toString(), radix));
        return total;
    }
    public static void main(String[] args) {
        ArrayList<ArrayList<String>> arr = new ArrayList<ArrayList<String>>();

        try {
            Scanner in = new Scanner(new InputStreamReader(System.in));
            while (in.hasNextLine()) {
                String cur = in.nextLine();
                Scanner curIn = new Scanner(cur);

                ArrayList<String> line = new ArrayList<String>();

                while (curIn.hasNext()) {
                    String a = curIn.next();
                    line.add(parse(a));
                }

                arr.add(line);
            }
        } catch (IOException e) {
            System.err.println("I / O error");
        }

        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i) != null) {
                for (int j = arr.get(i).size() - 1; j >= 0; j--) {
                    System.out.print(arr.get(i).get(j) + " ");
                }
                System.out.println();
            }
        }

    }
}