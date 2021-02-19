package table.expression;

public class Subtract extends BinaryExpression {
    public Subtract(Expression left, Expression right) {
        super(left, right, "-");
    }

    @Override
    protected int count(int x, int y) {
        return x - y;
    }
}