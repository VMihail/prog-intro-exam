package table.expression;

import java.util.List;
import java.util.Objects;

public class UnaryMinus implements Expression{
    private final Expression left;
    private Integer cache = null;

    public UnaryMinus(Expression left) {
        this.left = left;
    }

    @Override
    public String toString(){
        return  "-(" + left.toString() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UnaryMinus that = (UnaryMinus) o;
        return Objects.equals(left, that.left);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left);
    }

    @Override
    public int evaluate(List<Expression> cells) {
        if (cache == null) {
            cache = -(left.evaluate(cells));
        }
        return cache;
    }
}
