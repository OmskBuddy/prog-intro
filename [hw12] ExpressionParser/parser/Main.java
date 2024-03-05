package expression.parser;

public class Main {
    public static void main(String[] args) {
        String s = "(0 set 0)\n";
        System.out.println(s);
        ExpressionParser p = new ExpressionParser();
        try {
            System.out.println(p.parse(s).evaluate(1, 0, 0));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
