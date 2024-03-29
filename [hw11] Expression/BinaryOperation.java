package expression;

public abstract class BinaryOperation implements CommonExpression {

    protected final CommonExpression a;
    protected final CommonExpression b;
    protected char sign;

    public BinaryOperation(CommonExpression a, CommonExpression b) {
        this.a = a;
        this.b = b;
    }
    @Override
    public String freedomToString() {
        return a.toString() + " " + sign + " " + b.toString();
    }

    @Override
    public abstract int evaluate(int x);

    @Override
    public String toMiniString(){
        String newA = a.toMiniString();
        String newB = b.toMiniString();
        StringBuilder result = new StringBuilder();

        if (a.getQueue(true) < this.getQueue(false)) {
            result.append("(").append(newA).append(")");
        } else {
            result.append(newA);
        }

        result.append(" ").append(sign).append(" ");

        if (b.getQueue(false) < this.getQueue(true)) {
            result.append("(").append(newB).append(")");
        } else {
            result.append(newB);
        }
        return result.toString();
    };

    @Override
    public String toString() {
        return "(" + freedomToString() + ")";
    }

    @Override
    public boolean equals(Object that) {
        return that != null &&
                this.getClass() == that.getClass() &&
                this.hashCode() == ((BinaryOperation)that).hashCode();
    }

    @Override
    public int hashCode() {
        return ((a.hashCode() * 17) + b.hashCode()) * 17 + sign;
    }
}
