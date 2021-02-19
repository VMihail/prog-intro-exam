package table.expression;

public class Divide extends BinaryExpression {
    public Divide(Expression left, Expression right) {
        super(left, right, "/");
    }

    @Override
    protected int count(int x, int y) {
        return x / y;
    }
}
