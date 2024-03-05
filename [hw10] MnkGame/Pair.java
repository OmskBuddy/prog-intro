package game;

import java.io.PrintStream;
import java.util.Scanner;

public class Pair {
    public int first;
    public int second;

    public Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return first;
    }

    public int getSecond() {
        return second;
    }

    public static Pair enterPair(PrintStream out, Scanner in) {
        while (true) {
            String s1 = in.next();
            String s2 = in.next();
            Scanner sc1 = new Scanner(s1);
            Scanner sc2 = new Scanner(s2);
            if (!sc1.hasNextInt() || !sc2.hasNextInt()) {
                out.println("Incorrect format of input. Try again.");
                continue;
            }
            int fi = sc1.nextInt();
            int se = sc2.nextInt();
            return new Pair(fi, se);
        }
    }
}
