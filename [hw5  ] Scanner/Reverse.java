import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Reverse {
    public static void main(String[] args) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();
        /*
        String str = System.lineSeparator();
        for (char c : str.toCharArray()) {
            System.err.println((int)c);
        }
        System.err.println();
        */
        try {
            while (in.hasNextLine()) {
                StringBuilder cur = in.nextLine();
                Scanner curIn = new Scanner(cur);

                //System.err.println(cur);

                ArrayList<Integer> line = new ArrayList<Integer>();

                while (curIn.hasNext()) {
                    int a = curIn.nextInt();
                    line.add(a);
                }

                arr.add(line);
            }
        } catch (IOException e) {
            System.err.println("I / O error");
        }

        for (int i = arr.size() - 1; i >= 0; i--) {
            if (arr.get(i).size() != 0) {
                for (int j = arr.get(i).size() - 1; j >= 0; j--) {
                    System.out.print(arr.get(i).get(j) + " ");
                    //System.err.print(arr.get(i).get(j) + " ");
                }
            }
            System.out.println();
            //System.err.println();
        }

    }
}