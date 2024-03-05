public class Sum {
    public static void main(String[] args) {
        int result = 0;
        for (int i = 0; i < args.length; i++) {
            int start = 0;
            int len = 0;
            for (int j = 0; j < args[i].length(); j++) {
                if (Character.isWhitespace(args[i].charAt(j))) {
                    if (len != 0) {
                        result += Integer.parseInt(args[i].substring(start, start + len));
                    }
                    start = j + 1;
                    len = 0;
                } else {
                    len++;
                } 
            }
            if (len != 0) {
                result += Integer.parseInt(args[i].substring(start, start + len));
            }
        }
        System.out.println(result);
    }
}