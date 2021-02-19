package table.expression;

public class Add extends BinaryExpression {
    public Add(Expression left, Expression right) {
        super(left, right, "+");
    }

    @Override
    protected int count(int x, int y) {
        return x + y;
    }
}