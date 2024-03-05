import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] arr = new int[1000000][];
        int last = 999999;

        while (in.hasNextLine()) {
            String cur = in.nextLine();
            Scanner curIn = new Scanner(cur);
            int n = 0;
            int j = 0;

            while (curIn.hasNextInt()) {
                n++;
                int x = curIn.nextInt();
            }

            arr[last] = new int[n];
            curIn = new Scanner(cur);

            while (curIn.hasNextInt()) {
                arr[last][j] = curIn.nextInt();
                j++;
            }
            arr[last] = reverse1(arr[last]);
            last--;
        }

        for (int i = last + 1; i < arr.length; i++) {
            if (arr[i] != null) {
                for (int j = 0; j < arr[i].length; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
        }

    }
}