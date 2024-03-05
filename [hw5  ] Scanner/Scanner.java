import java.io.*;
import java.lang.StringBuilder;
import java.lang.NumberFormatException;
import java.util.HashSet;
import java.util.Arrays;

public class Scanner {

    final int SIZE = 1024;
    //final char LINE_BREAK_R = '\r';
    //final char LINE_BREAK_N = '\n';
    final StringBuilder LINE_SEPARATOR = new StringBuilder(System.lineSeparator());
    final char LINE_BREAK = LINE_SEPARATOR.charAt(LINE_SEPARATOR.length() - 1);
    final char APOSTR = '\'';
    
    private BufferedReader reader;
    boolean isOnlyNumsAndLetters = false;
    boolean isR = false;

    public Scanner(InputStreamReader a) {
        this.reader = new BufferedReader(a);
    }

    public Scanner(StringBuilder a) {
        this.reader = new BufferedReader(new StringReader(a.toString()));
    }

    public Scanner(InputStreamReader a, boolean isOnlyNumsAndLetters) {
        this.reader = new BufferedReader(a);
        this.isOnlyNumsAndLetters = isOnlyNumsAndLetters;
    }

    public Scanner(StringBuilder a, boolean isOnlyNumsAndLetters) {
        this.reader = new BufferedReader(new StringReader(a.toString()));
        this.isOnlyNumsAndLetters = isOnlyNumsAndLetters;
    }

    char[] buff = new char[SIZE];
    int leftBoard = 0;
    int rightBoard = 0;

    StringBuilder word = new StringBuilder();
    StringBuilder line = new StringBuilder();

    private Boolean isDilimiter(char x) {
        if (isOnlyNumsAndLetters) {
            return !Character.isLetter(x) && 
            Character.getType(x) != Character.DASH_PUNCTUATION && x != APOSTR;
        }
        return Character.isWhitespace(x);
    }

    private boolean scan() throws IOException {
        if (leftBoard >= rightBoard) {
            rightBoard = reader.read(buff);
            leftBoard = 0;
            if (rightBoard == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    public boolean hasNext() throws IOException {
        Boolean isFound = (word.length() != 0);
        while (!isFound && scan()) {
            if (!isDilimiter(buff[leftBoard])) {
                isFound = true;
                word.append(buff[leftBoard]);
            }
            leftBoard++;
        }
        return isFound;
    }

    public boolean hasNextLine() throws IOException {
        return scan();
    }

    public StringBuilder next() throws IOException {
        boolean isFound = false;
        while (!isFound && scan()) {
            if (isDilimiter(buff[leftBoard])) {
                isFound = true;
            } else {
                word.append(buff[leftBoard]);
            }
            leftBoard++;
        }

        StringBuilder result = word;
        word = word.delete(0, word.length());

        return result;
    }

    public int nextInt() throws IOException {
        StringBuilder str = next();
        return Integer.parseInt(str.toString());
    }

    public StringBuilder nextLine() throws IOException {
        boolean isFound = false;
        //StringBuilder nearSeparator = new StringBuilder();

        while (!isFound && scan()) {
            /*

            VARIANT 1

            if (line.length() != 0 && buff[leftBoard] == LINE_BREAK_R) {
                isR = true;
            } else if (isR) {
                if (buff[leftBoard] != LINE_BREAK_N) {
                    leftBoard--;
                }
                isFound = true;
            } else if (line.length() != 0 && !isR && buff[leftBoard] == LINE_BREAK_N) {
                isFound = true;
            } else if (buff[leftBoard] != LINE_BREAK_R ||
                    buff[leftBoard] != LINE_BREAK_N) {
                line.append(buff[leftBoard]);
            }

            leftBoard++;
            */

            /*

            VARIANT 2

            if (nearSeparator.length() == LINE_SEPARATOR.length()) {
                nearSeparator = "";
            }

            int n = nearSeparator.length();            
            for (int i = leftBoard; i < Math.min(rightBoard, leftBoard + LINE_SEPARATOR.length() - n); i++) {
                nearSeparator += Character.toString(buff[i]);
            }

            if (nearSeparator.equals(LINE_SEPARATOR)) {
                isFound = true;
                leftBoard++;
            } else {
                line.append(buff[leftBoard]);
            }

            leftBoard++;
            
            System.err.print(line.length() + "  ");
            for (char c : nearSeparator.toString().toCharArray()) {
                System.err.print((int)c + " ");
            } 
            for (char c : LINE_SEPARATOR.toCharArray()) {
                System.err.print((int)c + " ");
            }
            System.err.println(isFound);
            */

            if (buff[leftBoard] == LINE_BREAK) {
                isFound = true;
            } else {
                line.append(buff[leftBoard]);
            }
            leftBoard++;
        }

        StringBuilder result = line;
        line = line.delete(0, line.length());
        //isR = false;

        return result;
    }

    public void close() throws IOException {
        reader.close();
    }

}