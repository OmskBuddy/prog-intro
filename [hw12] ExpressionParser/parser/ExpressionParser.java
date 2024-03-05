package expression.parser;

import expression.*;

public class ExpressionParser implements TripleParser {

    @Override
    public TripleExpression parse(String expression) {
        //System.out.println(expression);
        return new Parser(new StringSource(expression)).getExpr();
    }

    private static class Parser extends BaseParser {

        private enum Operation {
            SET, CLEAR, GCD, LCM, PLUS, MINUS, MULT, DIV, NOTHING
        }

        protected Parser(CharSource source) {
            super(source);
        }

        public CommonExpression getExpr() {
            CommonExpression expr = getThirdLevel();
            if (eof()) {
                return expr;
            }
            throw new IllegalStateException("Expected end of expression, but there is " + getChar());
        }

        public CommonExpression getThirdLevel() {
            CommonExpression result = getSecondLevel();

            while (true) {
                Operation op = getOperation();
                switch (op) {
                    case SET -> {
                        result = new Set(result, getSecondLevel());
                    }
                    case CLEAR -> {
                        result = new Clear(result, getSecondLevel());
                    }
                    case GCD -> {
                        result = new Gcd(result, getSecondLevel());
                    }
                    case LCM -> {
                        result = new Lcm(result, getSecondLevel());
                    }
                    case NOTHING -> {
                        return result;
                    }
                    default -> throw new IllegalStateException("Unexpected operation in getCommonMultDiv: " + op);
                }
            }
        }

        public CommonExpression getSecondLevel() {
            CommonExpression result = getFirstLevel();

            while (true) {
                Operation op = getOperation();
                switch (op) {
                    case SET, CLEAR, GCD, LCM, NOTHING -> {
                        backOperation(op);
                        return result;
                    }
                    case PLUS -> {
                        result = new Add(result, getFirstLevel());
                    }
                    case MINUS -> {
                        result = new Subtract(result, getFirstLevel());
                    }
                    default -> throw new IllegalStateException("Unexpected operation in getPlusMinus: " + op);
                }
            }
        }

        public CommonExpression getFirstLevel() {
            CommonExpression result = getElem();

            while (true) {
                Operation op = getOperation();
                switch (op) {
                    case SET, CLEAR, GCD, LCM, PLUS, MINUS, NOTHING -> {
                        backOperation(op);
                        return result;
                    }
                    case MULT -> {
                        result = new Multiply(result, getElem());
                    }
                    case DIV -> {
                        result = new Divide(result, getElem());
                    }
                    default -> throw new IllegalStateException("Unexpected operation in getMultDiv: " + op);
                }
            }
        }

        public CommonExpression getElem() {
            skipWhitespaces();

            if (take('-')) {
                if (takeWhitespace()) {
                    return new UnaryMinus(getElem());
                }
                if (between('0', '9')) {
                    StringBuilder sb = new StringBuilder();
                    takeDigits(sb);
                    return new Const(getNumber(sb.toString(), true));
                } else {
                    return new UnaryMinus(getElem());
                }
            } else if (take('r')) {
                expect("everse");
                return new UnaryReverse(getElem());
            } else if (take('c')) {
                expect("ount");
                return new UnaryCount(getElem());
            } else if (between('0', '9')) {
                StringBuilder sb = new StringBuilder();
                takeDigits(sb);
                return new Const(getNumber(sb.toString(), false));
            } else if (between('x', 'z')) {
                return new Variable(take());
            } else if (take('(')) {
                CommonExpression result = getThirdLevel();
                expect(')');
                return result;
            }

            throw new IllegalStateException("Unexpected Element in getElem(): " + getChar());
        }

        private int getNumber(String digits, boolean isNegative) {
            int result = 0;
            int mult = isNegative ? -1 : 1;
            for (int i = 0; i < digits.length(); i++) {
                result *= 10;
                result += mult * Character.getNumericValue(digits.charAt(i));
            }
            return result;
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }


        private Operation getOperation() {
            skipWhitespaces();

            if (take('+')) {
                return Operation.PLUS;
            } else if (take('-')) {
                return Operation.MINUS;
            } else if (take('*')) {
                return Operation.MULT;
            } else if (take('/')) {
                return Operation.DIV;
            } else if (take('g')) {
                expect("cd");
                return Operation.GCD;
            } else if (take('l')) {
                expect("cm");
                return  Operation.LCM;
            } else if (take('s')) {
                expect("et");
                return Operation.SET;
            } else if (take('c')) {
                expect("lear");
                return Operation.CLEAR;
            } else if (test(')') || eof()) {
                return Operation.NOTHING;
            }

            throw new IllegalStateException("Unexpected operation " + getChar());
        }

        private void backOperation(Operation op) {
            int size = getOperationSize(op);
            back(size);
        }

        private int getOperationSize(Operation op) {
            int result = switch (op) {
                case PLUS, MINUS, MULT, DIV -> 1;
                case CLEAR -> 5;
                case GCD, LCM, SET -> 3;
                case NOTHING -> 0;
            };
            return result;
        }

        private void skipWhitespaces() {
            while (takeWhitespace()) {
                // skip
            }
        }
    }
}
