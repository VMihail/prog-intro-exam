package table.expression;

import java.util.List;
import java.util.Objects;

abstract class BinaryExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final String sign;
    private Integer cache = null;

    public BinaryExpression(Expression left, Expression right, String sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }

    protected abstract int count(int x, int y);

    @Override
    public String toString(){
        return '(' + left.toString() + " " + sign + " " + right.toString() + ')';
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right, getClass());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof BinaryExpression binaryExpression) {
            return sign.equals(binaryExpression.sign) && Objects.equals(left, binaryExpression.left)
                    && Objects.equals(right, binaryExpression.right);
        }
        return false;
    }

    @Override
    public int evaluate(List<Expression> cells) {
        if (cache == null) {
            cache = count(left.evaluate(cells), right.evaluate(cells));
        }
        return cache;
    }
}

