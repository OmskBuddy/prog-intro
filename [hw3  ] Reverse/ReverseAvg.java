import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class ReverseAvg {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> arr = new ArrayList<ArrayList<Integer>>();

        int numOfStr = 0;
        int weight = 0;

        while (in.hasNextLine()) {
            String str = in.nextLine();
            Scanner curIn = new Scanner(str);
            ArrayList<Integer> line = new ArrayList<Integer>();

            int n = 0;
            int pos = 0;

            while (curIn.hasNextInt()) {
                n++;
                line.add(curIn.nextInt());
            }

            weight = Math.max(weight, n);
            arr.add(line);

            numOfStr++;
        }

        int[] colCnt = new int[weight];
        long[] colSum = new long[weight];
        long[] strSum = new long[numOfStr];

        for (int i = 0; i < numOfStr; i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                colCnt[j]++;
                colSum[j] += arr.get(i).get(j);
                strSum[i] += arr.get(i).get(j);
            }
        }

        for (int i = 0; i < numOfStr; i++) {
            for (int j = 0; j < arr.get(i).size(); j++) {
                System.out.print(
                    (
                        (colSum[j] + strSum[i] - arr.get(i).get(j)) /
                        (colCnt[j] + arr.get(i).size() - 1)
                    ) + " "
                );
            }
            System.out.println();
        }
    }
}
