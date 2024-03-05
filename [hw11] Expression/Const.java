package expression;

public class Const implements CommonExpression {

    private final double value;
    private final boolean isInt;

    public Const(int value) {
        this.value = value;
        this.isInt = true;
    }

    public Const(double value) {
        this.value = value;
        this.isInt = false;
    }

    @Override
    public int evaluate(int x) {
        return (int)value;
    }

    @Override
    public double evaluate(double x) {
        return value;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return (int)value;
    }

    @Override
    public String toMiniString() {
        return this.toString();
    }

    @Override
    public String toString() {
        if (isInt) {
            return Integer.toString((int)value);
        } else {
            return Double.toString(value);
        }
    }

    @Override
    public boolean equals(Object that) {
        return that != null &&
                this.getClass() == that.getClass() &&
                this.hashCode() == ((Const) that).hashCode();
    }

    @Override
    public int hashCode() {
        return ((Number)value).hashCode();
    }

    @Override
    public int getQueue(boolean isFirstArg) {
        return 5;
    }

    @Override
    public String freedomToString() {
        return toMiniString();
    }
}