public class SumLongOctal {
    public static long convertString(String str, int start, int end) {
        int radix = 10;
        long result = 0;

        if (end - start > 0) {
            if (str.charAt(end - 1) == 'o' || str.charAt(end - 1) == 'O') {
                radix = 8;
                end--;
            }
            result = Long.parseLong(str.substring(start, end), radix);
        }

        return result;
    }

    public static void main(String[] args) {
        long result = 0;
        for (int i = 0; i < args.length; i++) {
            int start = 0;
            int end = 0;
            String curStr = args[i] + " ";
            for (int j = 0; j < curStr.length(); j++) {
                if (Character.isWhitespace(curStr.charAt(j))) {
                    result += convertString(curStr, start, end);
                    start = j + 1;
                    end = j + 1;
                } else {
                    end++;
                }
            }
        }
        System.out.println(result);
    }
}