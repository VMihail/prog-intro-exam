package table.expression;


import java.util.ArrayList;
import java.util.List;

public class ExpressionParser {
    private String expression;
    private int index = 0;
    private List<Expression> cells;

    private char current() {
        return expression.charAt(index);
    }

    private void skipCurrent() {
        index++;
    }

    public List<Expression> parse(String expression) {
        this.expression = expression;
        index = 0;
        cells = new ArrayList<>();

        startParsing();
        if (hasNonSpace()) {
            throw new IllegalArgumentException("A part of the expression left unparsed: " + expression.substring(index));
        }
        return cells;
    }

    private void startParsing() {
        parseCell();
    }

    private void parseCell() {
        Expression cell = parseAddSubtract();
        while (hasNonSpace()) {
            if (current() == '|') {
                skipCurrent();
                cells.add(cell);
                cell = parseAddSubtract();
            } else {
                break;
            }
        }
        cells.add(cell);
    }


    private Expression parseAddSubtract() {
        Expression left = parseMulDivide();
        Expression right;
        while (hasNonSpace()) {
            switch (current()) {
                case '+':
                    skipCurrent();
                    right = parseMulDivide();
                    left = new Add(left, right);
                    break;
                case '-':
                    skipCurrent();
                    right = parseMulDivide();
                    left = new Subtract(left, right);
                    break;
                default:
                    return left;
            }
        }
        return left;
    }

    private Expression parseMulDivide() {
        Expression left = parseHighestPriority();
        Expression right;
        while (hasNonSpace()) {
            switch (current()) {
                case '*':
                    skipCurrent();
                    right = parseHighestPriority();
                    left = new Multiply(left, right);
                    break;
                case '/':
                    skipCurrent();
                    right = parseHighestPriority();
                    left = new Divide(left, right);
                    break;
                default:
                    return left;
            }
        }
        return left;
    }

    private Expression parseHighestPriority() {
        if (!hasNonSpace()) {
            throw new IllegalArgumentException("Expression is not finished correctly!");
        }

        switch (current()) {
            case '-':
                if (index + 1 < expression.length() && Character.isDigit(expression.charAt(index + 1))) {
                    return new Const(parseInt());
                }
                skipCurrent();
                return new UnaryMinus(parseHighestPriority());

            case '(':
                skipCurrent();
                Expression inside = parseAddSubtract();
                if (!hasNonSpace() || current() != ')') {
                    throw new IllegalArgumentException("Bracket is not closed!");
                }
                skipCurrent();
                return inside;

            case '$':
                skipCurrent();
                return new Reference(parseInt());

            default:
                if (Character.isDigit(current())) {
                    return new Const(parseInt());
                }
                throw new IllegalArgumentException("Unexpected token: " + current());
        }
    }

    private boolean hasNonSpace() {
        while (index < expression.length() && Character.isWhitespace(expression.charAt(index))) {
            index++;
        }
        return index < expression.length();
    }

    private int parseInt() {
        int startIndex = index;
        if (current() == '-') {
            skipCurrent();
        }
        while (index < expression.length() && Character.isDigit(current())) {
            skipCurrent();
        }
        return Integer.parseInt(expression.substring(startIndex, index));
    }
}
