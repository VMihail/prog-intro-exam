package table.expression;

public class Multiply extends BinaryExpression {
    public Multiply(Expression left, Expression right) {
        super(left, right, "*");
    }

    @Override
    protected int count(int x, int y) {
        return x * y;
    }
}
