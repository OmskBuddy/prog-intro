package expression;

public class Main {
    public static void main(String[] args) {
        String result = new Add(
                new Subtract(
                    new Multiply(
                            new Variable("x"),
                            new Variable("x")
                    ),
                    new Multiply(
                            new Const(2),
                            new Variable("x")
                    )
                ),
                new Const(1)
        ).toMiniString();
        System.out.println(result);
    }
}