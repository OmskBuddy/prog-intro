package expression;

public interface CommonExpression extends Expression, DoubleExpression, TripleExpression {

    public int getQueue(boolean isFirstArg);

    public String freedomToString();
}
